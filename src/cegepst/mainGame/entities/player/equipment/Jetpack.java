package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.Camera;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.*;
import cegepst.mainGame.entities.items.FuelContainer;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.actions.JetpackActions;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Jetpack extends MovableEntity implements Equipable, SoundStopper, Animatable {

    private static final int MAX_FUEL = 100;
    private int fuel;
    private Player player;
    private Image sprite;
    private MovementController controller;
    private boolean isFlying;
    private Animator animator;
    private boolean isPlaySoundReady = true;

    public void initialize(Player player, GamePad controller) {
        sprite = ResourceLoader.loadSprite(Resource.JETPACK_SPRITE.getPath());
        this.controller = controller;
        this.player = player;
        fuel = MAX_FUEL;
        setDimension(16, 18);
        animator = new Animator(this, Resource.FLAME_SPRITE_SHEET, 1, JetpackActions.BURN, 16);
        EntityRepository.getInstance().registerEntityBuffered(this);
    }

    @Override
    public void updateEquipment() {
        animator.animate();
        playSoundEffect();
        horizontalDirection = player.getHorizontalDirection();
        updateState();
        fly();
        checkForContainer();
    }

    public void asyncDraw(Buffer buffer) {
        if (isFlying) {
            animator.drawFlippableAnimation(buffer, player.getX() - 5, player.getX() + 32, player.getY() + 45);
            animator.drawFlippableAnimation(buffer, player.getX() + 10, player.getX() + 15, player.getY() + 40);
        }
        buffer.drawFlippableImage(sprite, player.getX() - 15, player.getX() + 10, player.getY() - 8, 47, 57, player.getHorizontalDirection());
    }

    public void drawFuelGage(Buffer buffer, Camera camera) {
        buffer.drawRectangle(camera.getX() + 760, camera.getY() + 485, 35, 110, Color.DARK_GRAY);
        buffer.drawRectangle(camera.getX() + 764, camera.getY() + 489, 27, 102, Color.BLACK);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 490, 25, 100, Color.ORANGE);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 490, 25, (int) (((double) 100 / MAX_FUEL) * (MAX_FUEL - fuel)), Color.RED);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 510, 25, 2, Color.WHITE);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 530, 25, 2, Color.WHITE);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 550, 25, 2, Color.WHITE);
        buffer.drawRectangle(camera.getX() + 765, camera.getY() + 570, 25, 2, Color.WHITE);
    }

    public boolean isFlying() {
        return isFlying;
    }

    @Override
    public void update() {}

    @Override
    public void draw(Buffer buffer) {}

    @Override
    public boolean stopSound() {
        if (!isFlying) {
            isPlaySoundReady = true;
        }
        return !isFlying || player.isDead();
    }

    public void setIsPlaySoundReady(boolean isPlaySoundReady) {
        this.isPlaySoundReady = isPlaySoundReady;
    }

    @Override
    public boolean restartAnimation(Action currentAction) {
        return false;
    }

    @Override
    public void onAnimationEnd(Action action) {}

    @Override
    public String toString() {
        return "Jetpack";
    }

    private void playSoundEffect() {
        if (isFlying() && isPlaySoundReady) {
            Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.JETPACK_SOUND_EFFECT.getPath()), -20, this);
            isPlaySoundReady = false;
        }
    }

    private void updateState() {
        if ((controller.isJumpHeld() && fuel >= 0) && (player.getVerticalVelocity() <= 0 || isFlying)) {
            fuel--;
            isFlying = true;
            return;
        }
        isFlying = false;
        if (player.isGrounded() && player.hasKeptSameVelocity()) {
            fuel = MAX_FUEL;
        }
    }

    private void fly() {
        if (isFlying && player.getVerticalVelocity() < 0) {
            player.goBackUp();
        } else if (isFlying) {
            player.goUp();
        }
    }

    private void checkForContainer() {
        FuelContainer container = (FuelContainer) IntersectionChecker.checkIntersect(player,"FuelContainer");
        if (container != null && container.isPickable()) {
            fuel = MAX_FUEL;
            container.pickUp();
        }
    }
}


