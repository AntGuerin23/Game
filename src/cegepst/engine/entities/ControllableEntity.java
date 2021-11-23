package cegepst.engine.entities;

import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveAccordingToController() {
        if (!controller.isMoving()) {
            return;
        }
        if (controller.isUpPressed() && !controller.isDownPressed()) {
            moveUp();
        } else if (controller.isDownPressed() && !controller.isUpPressed()) {
            moveDown();
        } else if (controller.isLeftPressed() && !controller.isRightPressed()) {
            moveLeft();
        } else if (controller.isRightPressed() && !controller.isLeftPressed()) {
            moveRight();
        }
    }
}
