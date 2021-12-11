package cegepst.mainGame.miscellaneous.actions;

import cegepst.engine.resources.Action;

public enum RatActions implements Action {


    RUN(0, 8, 9, true),
    JUMP(1, 6, 8, false),
    STUNNED(2, 1, 5, false);

    private final int id;
    private final int nbOfFrames;
    private final int animationSpeed;
    private final boolean loop;

    RatActions(int id, int nbOfFrames, int animationSpeed, boolean loop) {
        this.id = id;
        this.nbOfFrames = nbOfFrames;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getNbOfFrames() {
        return nbOfFrames;
    }

    @Override
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    @Override
    public boolean loops() {
        return loop;
    }

    @Override
    public Action[] getEveryAction() {
        return cegepst.mainGame.miscellaneous.actions.RatActions.values();
    }
}
