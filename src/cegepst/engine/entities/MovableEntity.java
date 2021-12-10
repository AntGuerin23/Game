package cegepst.engine.entities;


import cegepst.engine.other.GameTime;
import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.resources.Action;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity {

    protected Collision collision;
    protected Direction horizontalDirection = Direction.RIGHT;
    protected Direction verticalDirection = Direction.DOWN;
    private double speed;
    protected double verticalVelocity;
    protected boolean moved;
    protected int lastX;
    protected int lastY;
    protected int stunStatus;
    private static final double gravityForce = 1.5;
    protected long firstAirFrameTime;
    private boolean wasGroundedLastFrame;
    protected boolean hasJustJumped;
    private double jumpForce;
    private boolean isGravitating = true;
    protected boolean isWallJumping;
    protected boolean isStuckToWall = false;
    protected boolean isStuckToWallReady = true;


    public MovableEntity() {
        collision = new Collision(this);
        moved = true;
        verticalVelocity = 0;
        firstAirFrameTime = GameTime.getCurrentTime();
        jumpForce = 5;
    }

    @Override
    public void update() {
        checkIfHasMoved();
        moveVertically();
    }

    public void moveLeft() {
        moveHorizontally(Direction.LEFT);
    }

    public void moveRight() {
        moveHorizontally(Direction.RIGHT);
    }

    public void moveHorizontally(Direction direction) {
        this.horizontalDirection = direction;
        double allowedSpeed = collision.getHorizontalAllowedSpeed(direction);
        x += direction.getVelocityX((int) allowedSpeed);
    }

    public void moveVertically() {
        if (isGravitating && !isStuckToWall) {
            jumpOrFall();
            undergoGravity();
            return;
        }
        hasJustJumped = false;
    }

    public void jump() {
        if (isGrounded() || isWallJumping) {
            startJumping();
            return;
        }
        hasJustJumped = false;
    }

    public Action getNextAction(Action currentAction) {
        return currentAction.getEveryAction()[0];
    }

    public void setJumpForce(double jumpForce) {
        this.jumpForce = jumpForce;
    }

    public void setGravitating(boolean gravitating) {
        isGravitating = gravitating;
    }

    public boolean isGrounded() {
        return collision.checkIfVerticallyStuck(true);
    }

    public boolean isTouchingWall() {
        if (collision.getHorizontalAllowedSpeed(horizontalDirection) == 0) {
            return true;
        }
        return false;
    }

    public boolean isPressingLeftAndRightButtons() {
        return false;
    }

    public double getSpeed() {
        return speed;
    }

    public int getVerticalVelocity() {
        return (int) verticalVelocity;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void drawHitBox(Buffer buffer) {
        drawHorizontalHitBox( buffer);
        drawVerticalHitBox(buffer);
    }

    public Rectangle getHorizontalHitBox() {
        switch (horizontalDirection) {
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
            default: return getBounds();
        }
    }

    public Rectangle getVerticalHitBox() {
        switch (verticalDirection) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            default : return getBounds();
        }
    }

    public void setHorizontalDirection(Direction horizontalDirection) {
        this.horizontalDirection = horizontalDirection;
    }

    public Direction getHorizontalDirection() {
        return horizontalDirection;
    }

    public Direction getVerticalDirection() {
        return verticalDirection;
    }

    //Check if intersects with inner bound
    public boolean hitBoxIntersectsWith(StaticEntity other) {
        return getHorizontalHitBox().intersects(other.getBounds());
    }

    public Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height, width,(int) Math.abs(verticalVelocity));
    }

    public Rectangle getUpperHitBox() {
        return new Rectangle(x, (int) (y - (int) Math.abs(verticalVelocity)), width, (int) Math.abs(verticalVelocity));
    }

    protected boolean isStuckToCeiling() {
        return collision.checkIfVerticallyStuck(false);
    }

    protected void checkIfHasMoved() {
        moved = (x != lastX || !isGrounded());
        lastX = x;
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle (x - (int) speed, y, (int) speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, (int) speed, height);
    }

    private void drawHorizontalHitBox(Buffer buffer) {
        Color color = new Color (255, 0, 0, 200);
        Rectangle horizontalHitBox = getHorizontalHitBox();
        buffer.drawRectangle(horizontalHitBox.x, horizontalHitBox.y, horizontalHitBox.width, horizontalHitBox.height, color);
    }

    private void drawVerticalHitBox(Buffer buffer) {
        Color color = new Color (255, 0, 0, 200);
        Rectangle verticalHitBox = getVerticalHitBox();
        buffer.drawRectangle(verticalHitBox.x, verticalHitBox.y, verticalHitBox.width, verticalHitBox.height, color);
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

    private void jumpOrFall() {
        if (isGrounded() && hasJustJumped) {
            leaveGround();
        } else {
            succumbToForces();
        }
    }

    private void startJumping() {
        verticalVelocity = jumpForce;
        hasJustJumped = true;
        verticalDirection = Direction.UP;
    }

    private void leaveGround() {
        y -= (int) verticalVelocity;
        hasJustJumped = false;
    }

    private void succumbToForces() {
        if (verticalVelocity < 0) {
            verticalDirection = Direction.DOWN;
        }
        int allowedSpeed = (int) collision.getVerticalAllowedSpeed(verticalDirection);
        y -= allowedSpeed;
        if (isStuckToCeiling()) {
            verticalVelocity = 0;
        }
    }

    private void checkIfIsFirstGroundedFrame() {
        if (wasGroundedLastFrame) {
            firstAirFrameTime = GameTime.getCurrentTime();
            if (verticalDirection == Direction.DOWN) {
                firstAirFrameTime -= 100;
                verticalVelocity = -8;
            }
        }
    }

    private void updateValues() {
        long timeInAir = GameTime.getTimeSince(firstAirFrameTime);
        verticalVelocity -= gravityForce * (timeInAir / (double) 1000);
        wasGroundedLastFrame = false;
    }

    private void capSpeed() {
        if (verticalVelocity < -15) {
            verticalVelocity = -15;
        }
    }

    private void updateLastGroundedFrame() {
        wasGroundedLastFrame = true;
    }
}
