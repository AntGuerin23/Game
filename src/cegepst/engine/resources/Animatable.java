package cegepst.engine.resources;

import cegepst.engine.controls.Direction;

public interface Animatable {
    Action getNextAction(Action currentAction);
    boolean restartAnimation(Action currentAction);
    void onAnimationEnd(Action action);
    int getHeight();
    int getWidth();
    Direction getHorizontalDirection();
}
