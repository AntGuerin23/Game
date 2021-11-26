package cegepst.engine.entities;

import cegepst.engine.controls.MovementController;

public abstract class ControllableEntity extends GravitatingEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void moveAccordingToController() {
        if (!controller.isMoving()) {
            return;
        }
//        if (controller.isUpPressed() && !controller.isDownPressed()) {
//            moveUp();
//        } else if (controller.isDownPressed() && !controller.isUpPressed()) {
//            moveDown();
        if (controller.isLeftHeld() && !controller.isRightHeld()) {
            moveLeft();
        } else if (controller.isRightHeld() && !controller.isLeftHeld()) {
            moveRight();
        }
    }
}
