package cegepst.engine.graphics;

import cegepst.engine.ResourceLoader;
import cegepst.engine.entities.MovableEntity;
import cegepst.mainGame.miscellaneous.other.Resource;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import sun.font.FontRunIterator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
    private static final int ANIMATION_SPEED = 8;
    private static final int RPG_MAKER_ANIMATION_FRAMES_NB = 3;
    private Image[][] frames;
    private BufferedImage spriteSheet;
    private int currentAnimationFrame = 0;
    private Action currentAction;
    private int nextFrame;
    private final MovableEntity entity;
    private int startingX;
    private int startingY;

    public Animator(MovableEntity entity, boolean isTopDown) {
        if (isTopDown) {
            initializeTopDownArray();
        } else {
            initialize2DArray();
        }
        nextFrame = ANIMATION_SPEED;
        this.entity = entity;
    }

    public void animate() {
        if (entity.hasMoved()) {
            setCurrentAction();
            continueAnimation();
            return;
        }
        idle();
    }

    private void setCurrentAction() {
        Action lastAction = currentAction;
        if (entity.isStunned()) {
            currentAction = Action.STUNNED;
        } else if (!entity.isGrounded()) {
            currentAction = Action.JUMP;
        } else {
            currentAction = Action.RUN;
        }
        //todo: add push condition

        if (lastAction != currentAction) {
            currentAnimationFrame = 0;
        }

    }

    public void drawTopDownAnimation(Buffer buffer) {
        buffer.drawImage(frames[entity.getHorizontalDirection().getAnimationID()][currentAnimationFrame], entity);
    }

    public void draw2DAnimation(Buffer buffer) {
        buffer.drawImage(frames[currentAction.getId()][currentAnimationFrame], entity);
    }

    public void load2DAnimationFrames(Resource resource) {
        loadSpriteSheet(resource,0,0);
        int totalFrameNb = 0;
        int loopFrameNb = 0;
        for (int i = 0; i < frames.length; i++) {
            do {
                frames[i][loopFrameNb++] = get2DFrame(totalFrameNb++);
            } while (loopFrameNb < frames[i].length);
            loopFrameNb = 0;
        }
    }

    private Image get2DFrame(int frameNb) {
        return spriteSheet.getSubimage(startingX + (frameNb * 42), 0, entity.getWidth(), entity.getHeight());
    }


    public void loadSpriteSheet(Resource resource, int startingX, int startingY) {
        spriteSheet = ResourceLoader.loadSpriteSheet(resource.getPath());
        this.startingX = startingX;
        this.startingY = startingY;
    }

    public void loadRPGMakerAnimationFrames(Resource resource, int startingX, int startingY) {
        loadSpriteSheet(resource,startingX,startingY);
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
            if (currentAnimationFrame >= currentAction.getNbOfFrames()) {
                currentAnimationFrame = (currentAction == Action.JUMP) ? Action.JUMP.getNbOfFrames() - 1 : 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    private void idle() {
        currentAction = Action.RUN;
        currentAnimationFrame = 0;
    }

    private void initialize2DArray() {
        frames = new Image[4][];
        for (Action action : Action.values()) {
            frames[action.getId()] = new Image[action.getNbOfFrames()];
        }
    }


    private void initializeTopDownArray() {
        frames = new Image[4][];
    }
}
