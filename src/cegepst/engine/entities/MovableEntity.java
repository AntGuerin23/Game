package cegepst.engine.entities;


import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity {

    private Collision collision;
    private Direction horizontalDirection = Direction.RIGHT;
    private Direction verticalDirection = Direction.DOWN;
    private int speed;
    protected double verticalVelocity;
    private double jumpForce;
    private boolean moved;
    private boolean firstJumpingFrame;
    private int lastX;
    private int lastY;
    private int groundedY;

    public MovableEntity() {
        collision = new Collision(this);
        speed = 1; //Default value
        jumpForce = 5; //Default value
        verticalVelocity = 0;
    }

    @Override
    public void update() {
        moved = false;
        moveVertically();
    }

    public void moveLeft() {
        moveHorizontally(Direction.LEFT);
    }

    public void moveRight() {
        moveHorizontally(Direction.RIGHT);
    }

    public void jump() {
        if (isGrounded()) {
            verticalVelocity = jumpForce;
            firstJumpingFrame = true;
            verticalDirection = Direction.UP;
        }
    }

    public void moveHorizontally(Direction direction) {
        this.horizontalDirection = direction;
        double allowedSpeed = collision.getAllowedSpeed(direction);
        x += direction.getVelocityX((int) allowedSpeed);
        y += direction.getVelocityY((int) allowedSpeed);
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    public void moveVertically() {
        if (isGrounded() && firstJumpingFrame) {
            y -= (int) verticalVelocity;
            firstJumpingFrame = false;
        } else {
            if (verticalVelocity < 0) {
                verticalDirection = Direction.DOWN;
            }
            y -= (int) collision.getAllowedDownSpeed();
        }
    }

    protected boolean isGrounded() {
        return collision.checkIfGrounded();
    }

    public int getSpeed() {
        return speed;
    }

    public int getVerticalVelocity() {
        return (int) verticalVelocity;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setJumpForce(double jumpForce) {
        this.jumpForce = jumpForce;
    }

    public void drawHitBox(Buffer buffer) {
        Color color = new Color (255, 0, 0, 200);
        Rectangle leftOrRightHitbox = getHitBox();
        buffer.drawRectangle(leftOrRightHitbox.x, leftOrRightHitbox.y, leftOrRightHitbox.width, leftOrRightHitbox.height, color);

        Rectangle lowerHitBox = getLowerHitBox();
        buffer.drawRectangle(lowerHitBox.x, lowerHitBox.y, lowerHitBox.width, lowerHitBox.height, color);
//        drawLeftOrRightHitbox();
//        drawDownOrUpHitbox();
    }

    public Rectangle getHitBox() {
        switch (horizontalDirection) {
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
//            case UP: return getUpperHitBox();
//            case DOWN: return getLowerHitBox();
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
        return getHitBox().intersects(other.getBounds());
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, speed, height);
    }

    public Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed, width, speed);
    }

    public Rectangle getLowerHitBox() {
            return new Rectangle(x, y + height, width, Math.max(1, (int) Math.abs(verticalVelocity)));
    }
}
