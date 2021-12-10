package cegepst.mainGame.miscellaneous.actions;

import cegepst.engine.resources.Action;

public enum PlayerActions implements Action {

    RUN(0, 9, 8, true),
    PUSH(1, 6, 8, true),
    JUMP(2, 12, 8, false),
    STUNNED(3, 12, 5, true);

    private final int id;
    private final int nbOfFrames;
    private final int animationSpeed;
    private final boolean loop;

    PlayerActions(int id, int nbOfFrames, int animationSpeed, boolean loop) {
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
        return PlayerActions.values();
    }
}
