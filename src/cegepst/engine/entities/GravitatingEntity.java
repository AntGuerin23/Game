package cegepst.engine.entities;

import cegepst.engine.GameTime;

public abstract class GravitatingEntity extends MovableEntity {

    private static final double gravityForce = 2;
    private long firstAirFrameTime;
    private boolean wasGroundedLastFrame;

    public GravitatingEntity() {
        firstAirFrameTime = GameTime.getCurrentTime();
    }

    @Override
    public void update() {
        super.update();
        undergoGravity();
    }

    private void undergoGravity() {
        if (!isGrounded()) {
            if (wasGroundedLastFrame) {
                firstAirFrameTime = GameTime.getCurrentTime();
            }
            long timeInAir = GameTime.getTimeSince(firstAirFrameTime);
            super.verticalVelocity -= gravityForce * (timeInAir / (double) 1000);
            wasGroundedLastFrame = false;
            if (super.verticalVelocity < -10) {
                super.verticalVelocity = -10;
            }
            return;
        }

        //super.verticalVelocity = 0;
        wasGroundedLastFrame = true;
    }
}
