package cegepst.engine.entities;

import cegepst.engine.GameTime;
import cegepst.engine.controls.Direction;
import sun.reflect.CallerSensitive;

public abstract class GravitatingEntity extends MovableEntity {

    private static final double gravityForce = 1.5;
    private long firstAirFrameTime;
    private boolean wasGroundedLastFrame;
    private boolean firstJumpingFrame;
    private double jumpForce;
    private int lastAllowedSpeed;


    public GravitatingEntity() {
        firstAirFrameTime = GameTime.getCurrentTime();
        jumpForce = 5; //Default value
        lastAllowedSpeed = 0;
    }

    @Override
    public void update() {
        super.update();
        undergoGravity();
    }

    public void jump() {
        if (isGrounded()) {
            startJumping();
        }
    }

    @Override
    public void moveVertically() {
        if (isGrounded() && firstJumpingFrame) {
            leaveGround();
        } else {
            succumbToForces();
        }
    }

    public void setJumpForce(double jumpForce) {
        this.jumpForce = jumpForce;
    }

    private void undergoGravity() {
        if (!isGrounded()) {
            checkIfIsFirstGroundedFrame();
            updateValues();
            capSpeed();
            return;
        }
        updateLastGroundedFrame();
    }

    private void startJumping() {
        verticalVelocity = jumpForce;
        firstJumpingFrame = true;
        super.verticalDirection = Direction.UP;
    }

    private void leaveGround() {
        y -= (int) super.verticalVelocity;
        firstJumpingFrame = false;
    }

    private void succumbToForces() {
        if (super.verticalVelocity < 0) {
            super.verticalDirection = Direction.DOWN;
        }
        int allowedSpeed = (int) super.collision.getVerticalAllowedSpeed(super.verticalDirection);
        y -= allowedSpeed;
        if (isStuckToCeiling()) {
            super.verticalVelocity = -2;
            super.y += 2;
        }
        lastAllowedSpeed = allowedSpeed;
    }

    private void checkIfIsFirstGroundedFrame() {
        if (wasGroundedLastFrame) {
            firstAirFrameTime = GameTime.getCurrentTime();
            if (super.verticalDirection == Direction.DOWN) {
                firstAirFrameTime -= 100;
                verticalVelocity = -8;
            }
        }
    }

    private void updateValues() {
        long timeInAir = GameTime.getTimeSince(firstAirFrameTime);
        super.verticalVelocity -= gravityForce * (timeInAir / (double) 1000);
        wasGroundedLastFrame = false;
    }

    private void capSpeed() {
        if (super.verticalVelocity < -15) {
            super.verticalVelocity = -15;
        }
    }

    private void updateLastGroundedFrame() {
        wasGroundedLastFrame = true;
    }
}
