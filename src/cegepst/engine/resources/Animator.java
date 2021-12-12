package cegepst.engine.resources;

import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animator {
    private Action currentAction;
    private Action defaultAction;
    private Animatable entity;
    private BufferedImage spriteSheet;
    private Image[][] frames;
    private int currentAnimationFrame;
    private int nextFrame;
    private int nbOfActions;
    private int nbOfPixels;

    public Animator(Animatable entity, Resource resource, int nbOfActions, Action defaultAction, int nbOfPixels) {
        initializeValues(entity, nbOfActions, defaultAction, nbOfPixels);
        initializeFrameArray();
        loadSpriteSheet(resource);
        loadAnimationFrames();
    }

    public void animate() {
        getActionFromEntity();
        playAnimation();
    }

    public void drawAnimation(Buffer buffer, int x, int y) {
        buffer.drawImage(frames[currentAction.getId()][currentAnimationFrame], x, y);
    }

    public void drawFlippableAnimation(Buffer buffer, int x, int flippedX, int y) {
        buffer.drawFlippableImage(frames[currentAction.getId()][currentAnimationFrame], x, flippedX, y, entity.getWidth(), entity.getHeight(), entity.getHorizontalDirection());
    }

    public void loadSpriteSheet(Resource resource) {
        spriteSheet = ResourceLoader.loadBufferedImage(resource.getPath());
    }

    private void playAnimation() {
        if (!entity.restartAnimation(currentAction)) {
            continueAnimation();
            return;
        }
        currentAnimationFrame = 0;
    }

    private Image getSpriteSheetFrame(int frameNb) {
        return spriteSheet.getSubimage((frameNb * nbOfPixels), 0, entity.getWidth(), entity.getHeight());
    }

    private boolean animationIsStopped() {
        return currentAction == null;
    }

    private void initializeValues(Animatable entity, int nbOfActions, Action defaultAction, int nbOfPixels) {
        this.currentAction = defaultAction;
        this.nbOfActions = nbOfActions;
        this.defaultAction = defaultAction;
        this.nbOfPixels = nbOfPixels;
        this.entity = entity;
        nextFrame = defaultAction.getAnimationSpeed();
        currentAnimationFrame = 0;
    }

    private void getActionFromEntity() {
        currentAction = entity.getNextAction(currentAction);
        if (animationIsStopped()) {
            currentAction = defaultAction;
            currentAnimationFrame = 0;
        }
    }

    private void initializeFrameArray() {
        frames = new Image[nbOfActions][];
        for (Action action : currentAction.getEveryAction()) {
            frames[action.getId()] = new Image[action.getNbOfFrames()];
        }
    }

    private void loadAnimationFrames() {
        int totalFrameNb = 0;
        int loopFrameNb = 0;
        for (int i = 0; i < frames.length; i++) {
            do {
                frames[i][loopFrameNb++] = getSpriteSheetFrame(totalFrameNb++);
            } while (loopFrameNb < frames[i].length);
            loopFrameNb = 0;
        }
    }

    private void continueAnimation() {
        --nextFrame;
        if (nextFrame == 0) {
            changeFrame();
        }
    }

    private void changeFrame() {
        ++currentAnimationFrame;
        if (currentAnimationFrame >= currentAction.getNbOfFrames()) {
            currentAnimationFrame = (!currentAction.loops()) ? currentAction.getNbOfFrames() - 1 : 0;
            entity.onAnimationEnd(currentAction);
        }
        nextFrame = currentAction.getAnimationSpeed();
    }
}
