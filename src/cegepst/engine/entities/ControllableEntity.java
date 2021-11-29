package cegepst.engine.entities;

import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends GravitatingEntity {

    private final MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveAccordingToController() {
        checkJump();
        moveLeftAndRight();
    }

    private void checkJump() {
        if (controller.isJumpPressed()) {
            jump();
        }
    }

    private void moveLeftAndRight() {
        if (controller.isLeftHeld() && !controller.isRightHeld()) {
            moveLeft();
        } else if (controller.isRightHeld() && !controller.isLeftHeld()) {
            moveRight();
        }
    }
}
