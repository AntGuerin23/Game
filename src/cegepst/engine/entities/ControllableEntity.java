package cegepst.engine.entities;

import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    protected final MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveAccordingToController() {
        checkJump();
        moveLeftAndRight();
    }

    @Override
    public boolean isPressingLeftAndRightButtons() {
        return controller.isLeftHeld() || controller.isRightHeld();
    }

    private void checkJump() {
        if (controller.isJumpPressed()) {
            if (isStuckToWall) {
                isWallJumping = true;
                isStuckToWall = false;
                isStuckToWallReady = false;
                verticalVelocity = 0;
            }
            jump();
        } else {
            isWallJumping = false;
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
