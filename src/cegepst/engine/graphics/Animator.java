package cegepst.engine.graphics;

import cegepst.engine.ResourceLoader;
import cegepst.engine.entities.MovableEntity;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
    private static final int ANIMATION_SPEED = 8;
    private static final int RPG_MAKER_ANIMATION_FRAMES_NB = 3;
    private Image[][] frames;
    private final BufferedImage spriteSheet;
    private int currentAnimationFrame = 1; //idle frame
    private int nextFrame;
    private final MovableEntity entity;
    private final int startingX;
    private final int startingY;

    public Animator(Resource resource, MovableEntity entity, int startingX, int startingY) {
        initializeArray();
        spriteSheet = ResourceLoader.loadSpriteSheet(resource);
        nextFrame = ANIMATION_SPEED;
        this.entity = entity;
        this.startingX = startingX;
        this.startingY = startingY;
    }

    public void animate() {
        if (entity.hasMoved()) {
            continueAnimation();
            return;
        }
        idle();
    }

    public void drawTopDownAnimation(Buffer buffer) {
        buffer.drawImage(frames[entity.getHorizontalDirection().getAnimationID()][currentAnimationFrame], entity.getX(), entity.getY());
    }

    public void loadRPGMakerAnimationFrames() {
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Image[RPG_MAKER_ANIMATION_FRAMES_NB];
            for (int j = 0; j < frames[i].length; j++) {
                frames[i][j] = spriteSheet.getSubimage(startingX + j * 32 , startingY + (i * 32), entity.getWidth(), entity.getHeight());
            }
        }
    }

    private void continueAnimation() {
        --nextFrame;
        if (nextFrame == 0) {
            ++currentAnimationFrame;
            if (currentAnimationFrame >= RPG_MAKER_ANIMATION_FRAMES_NB) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    private void idle() {
        currentAnimationFrame = 1; //Back to idle frame
    }

    private void initializeArray() {
        frames = new Image[4][];
    }
}
