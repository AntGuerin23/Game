package cegepst.mainGame.miscellaneous.actions;

import cegepst.engine.resources.Action;

public enum JetpackActions implements Action {
    BURN(0, 4, 4, true);

    private final int id;
    private final int nbOfFrames;
    private final int animationSpeed;
    private final boolean loop;

    JetpackActions(int id, int nbOfFrames, int animationSpeed, boolean loop) {
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
    public boolean loops() {
        return loop;
    }

    @Override
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    @Override
    public Action[] getEveryAction() {
        return JetpackActions.values();
    }
}
