package cegepst.mainGame.entities.player;

import cegepst.engine.controls.Direction;
import cegepst.engine.other.Camera;
import cegepst.engine.other.GameTime;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.resources.*;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.items.coin.CoinRespawner;
import cegepst.mainGame.entities.items.coin.DroppedCoin;
import cegepst.mainGame.entities.player.equipment.Inventory;
import cegepst.mainGame.miscellaneous.actions.PlayerActions;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Player extends ControllableEntity implements Animatable {

    private final static int MAX_HP = 5;
    private final static int STUN_DURATION = 60;
    private int coinCount;
    private int hp;
    private Animator animator;
    private Action lastAction;
    private final Inventory inventory;
    private boolean hasLanded = false;
    private boolean stunned;
    private double lastVerticalVelocity;
    private static Player instance;
    private Point lastGroundedLocation;

    public static Player getInstance(GamePad controller) {
        if (instance == null) {
            instance = new Player(controller);
        }
        return instance;
    }

    @Override
    public void draw(Buffer buffer) {
        inventory.drawJetpack(buffer);
        drawPlayer(buffer);
        inventory.drawShotgun(buffer);
    }

    @Override
    public void update() {
        super.update();
        super.moveAccordingToController();
        updateValues();
        updateResources();
    }

    @Override
    public Action getNextAction(Action currentAction) {
        return updateAction();
    }


    public void foundCoin(int nbOfCoins) {
        coinCount += nbOfCoins;
    }

    public void respawn() {
        hp = MAX_HP;
        isDead = false;
        stunStatus = 0;
        EntityRepository.getInstance().registerEntity(this,false);
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getHp() {
        return hp;
    }

    public boolean restartAnimation(Action currentAction) {
        boolean restart = (lastAction != currentAction || (!isPressingLeftAndRightButtons() && isGrounded()
                && currentAction != PlayerActions.STUNNED) || inventory.isJetpackFlying() || isStuckToWall);
        lastAction = currentAction;
        return restart;
    }

    public boolean hasKeptSameVelocity() {
        return verticalVelocity == lastVerticalVelocity;
    }

    public void goUp() {
        verticalDirection = Direction.UP;
        verticalVelocity += 2;
        if (verticalVelocity > 4) {
            verticalVelocity = 4;
        }
        firstAirFrameTime = GameTime.getCurrentTime() - 250;
    }

    public void goBackUp() {
        verticalDirection = Direction.UP;
        verticalVelocity += 3;
    }

    public void pickupShotgun() {
        inventory.pickupShotgun();
    }

    public void pickupJetpack() {
        inventory.pickupJetpack();
    }

    public void pickupClimbingGloves() {
        inventory.pickupClimbingGloves();
    }

    public void drawStaticImages(Buffer buffer, Camera camera) {
        drawHealthBar(buffer, camera);
        if (inventory.hasJetpack()) {
            inventory.drawFuelGage(buffer, camera);
        }
    }

    @Override
    public String toString() {
        return "Player";
    }

    public void climb() {
        if (isClimbing()) {
            isStuckToWall = true;
            firstAirFrameTime = GameTime.getCurrentTime();
            verticalVelocity = -5;
        } else {
            isStuckToWall = false;
        }
        if (verticalVelocity <= 0) {
            isStuckToWallReady = true;
        }
    }

    @Override
    public void onDeath() {
        for (int i = 0; i < coinCount; i++) {
            new DroppedCoin(x, y, this);
        }
        coinCount = 0;
    }

    @Override
    public void onAnimationEnd(Action action) {}

    private void updateValues() {
        manageDamage();
        inventory.update();
        saveVelocity();
        updateLastGroundedLocation();
    }

    private void updateResources() {
        playSoundEffects();
        animator.animate();
    }

    private void manageDamage() {
        updateStun();
        checkIfDamaged();
        checkIfDead();
    }

    private void updateLastGroundedLocation() {
        if (isGrounded()) {
            CoinRespawner.getInstance().updatePlayerLocation(new Point(x, y), this);
        }
    }

    private void drawPlayer(Buffer buffer) {
        animator.drawFlippableAnimation(buffer, x, x, y);
        if (hasMoved() && GameSettings.debug) {
            drawHitBox(buffer);
        }
    }

    private void initializeValues() {
        coinCount = 0;
        hp = MAX_HP;
        setSpeed(5);
        setDimension(42,48);
        setJumpForce(10);
    }

    private void playSoundEffects() {
        playLandSound();
        playJumpSound();
    }

    private void playLandSound() {
        if (hasLanded && !isGrounded()) {
            hasLanded = false;
        }
        if (isGrounded() && !hasLanded && verticalDirection == Direction.DOWN) {
            Sound.playOnce(ResourceLoader.loadSound(Resource.LAND_SOUND_EFFECT.getPath()), -15);
            hasLanded = true;
        }
    }

    private void playJumpSound() {
        if (hasJustJumped && !isStuckToWall) {
            Sound.playOnce(ResourceLoader.loadSound(Resource.JUMP_SOUND_EFFECT.getPath()), -5);
        }
        if (hasJustJumped) {
            hasJustJumped = false;
        }
    }

    private Action updateAction() {
        if (stunStatus > 0) {
            return PlayerActions.STUNNED;
        }
        if (inventory.isJetpackFlying()) {
            return PlayerActions.RUN;
        }
        if (!isGrounded()) {
            return PlayerActions.JUMP;
        }
        if (isTouchingWall() && isPressingLeftAndRightButtons()) {
            return PlayerActions.PUSH;
        }
        return PlayerActions.RUN;
    }

    private void updateStun() {
        if (stunStatus <= 0) {
            stunned = false;
        }
        stunStatus--;
    }

    private void saveVelocity() {
        lastVerticalVelocity = verticalVelocity;
    }

    private boolean isClimbing() {
        return controller.isUpHeld() && isTouchingWall() && !isGrounded() && !isWallJumping && isStuckToWallReady;
    }

    private void checkIfDamaged() {
        checkForEnemyHit();
        checkForSpikeHit();
    }

    private void checkForEnemyHit() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Enemy");
        if (intersectingEntity != null && !stunned) {
            getHit(1);
            stunned = true;
            stunStatus = STUN_DURATION;
        }
    }

    private void checkForSpikeHit() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Spike");
        if (intersectingEntity != null) {
            getHit(999);
        }
    }

    private void drawHealthBar(Buffer buffer, Camera camera) {
        buffer.drawRectangle(camera.getX() + 8, camera.getY() + 10, 40 + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(camera.getX() + 10, camera.getY() + 12, 40, 10,  Color.RED);
        buffer.drawRectangle(camera.getX() + 10, camera.getY() + 12, (int) (((double)40 / MAX_HP) * hp), 10, Color.GREEN);
    }

    private void getHit(int damage) {
        hp -= damage;
        Sound.playOnce(ResourceLoader.loadSound(Resource.DAMAGE_SOUND_EFFECT.getPath()),-10);
    }

    private void checkIfDead() {
        if (hp <= 0) {
            isDead = true;
        }
    }

    private Player(GamePad controller) {
        super(controller);
        EntityRepository.getInstance().registerEntity(this,false);
        initializeValues();
        animator = new Animator(this, Resource.PLAYER_SPRITE_SHEET, 4, PlayerActions.RUN, 42);
        inventory = new Inventory(this, controller);
    }
}
