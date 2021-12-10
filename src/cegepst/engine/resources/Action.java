package cegepst.engine.resources;

public interface Action {
    int getId();
    int getNbOfFrames();

    int getAnimationSpeed();

    boolean loops();

    Action[] getEveryAction();
}
