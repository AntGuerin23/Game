package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Animator;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.miscellaneous.other.Resource;

public class Player extends ControllableEntity {

    private final static int MAX_HP = 3;
    private final static int STUN_DURATION = 30;
    private int coinCount;
    private int hp;
    private Animator animator;

    public Player(MovementController controller) {
        super(controller);
        EntityRepository.getInstance().registerEntity(this,false);
        initializeValues();
        animator = new Animator( this, false);
        animator.load2DAnimationFrames(Resource.PLAYER_SPRITE_SHEET);
    }

    @Override
    public void draw(Buffer buffer) {
        animator.draw2DAnimation(buffer);
//        buffer.drawRectangle(x, y, width, height, Color.ORANGE);
        if (hasMoved() && GameSettings.debug) {
            drawHitBox(buffer);
        }

    }

    @Override
    public void update() {
        super.update();
        move();
        checkIfDamaged();
        checkIfDead();
        animator.animate();
    }

    public void foundCoin(int nbOfCoins) {
        coinCount += nbOfCoins;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Player";
    }

    private void initializeValues() {
        coinCount = 0;
        hp = MAX_HP;
        setSpeed(5);
        setDimension(42,48);
        setJumpForce(10);
    }

    private void checkIfDamaged() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Enemy");
        if (intersectingEntity != null && !stunned) {
            getHit(1);
            stunned = true;
            stunStatus = STUN_DURATION;
            stunDirection = getHorizontalDirection().revert();
        }
    }

    private void getHit(int damage) {
        hp -= damage;
    }

    private void checkIfDead() {
        if (hp == 0) {
            isDead = true;
        }
    }

    private void move() {
        if (!stunned) {
            moveAccordingToController();
            return;
        }
        if (stunStatus > 0) {
            stunStatus--;
            moveHorizontally(stunDirection);
            return;
        }
        stunned = false;
    }
}
