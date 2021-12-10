package cegepst.mainGame.miscellaneous.actions;

import cegepst.engine.resources.Action;

public enum CoinEffectActions implements Action {

    DISAPPEAR(0, 8, 2, true);

    private final int id;
    private final int nbOfFrames;
    private final int animationSpeed;
    private final boolean loop;

    CoinEffectActions(int id, int nbOfFrames, int animationSpeed, boolean loop) {
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
        return CoinEffectActions.values();
    }
}
