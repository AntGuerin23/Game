package cegepst.engine.entities;


import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity {

    protected Collision collision;
    protected Direction horizontalDirection = Direction.RIGHT;
    protected Direction verticalDirection = Direction.DOWN;
    private int speed;
    protected double verticalVelocity;
    private boolean moved;
    private int lastX;
    private int lastY;

    public MovableEntity() {
        collision = new Collision(this);
        moved = true;
        verticalVelocity = 0;
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
        //TODO : Add ability for non-gravitating enemies to fly
    }

    protected boolean isGrounded() {
        return collision.checkIfVerticallyStuck(true);
    }

    protected boolean isStuckToCeiling() {
        return collision.checkIfVerticallyStuck(false);
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
        return new Rectangle(x, y - speed, width, (int) Math.abs(verticalVelocity));
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, speed, height);
    }

    private void checkIfHasMoved() {
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
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
}
