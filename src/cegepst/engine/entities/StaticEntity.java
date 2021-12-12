package cegepst.engine.entities;

import cegepst.engine.graphics.Buffer;

import java.awt.*;

public abstract class StaticEntity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isDead;

    public abstract void draw(Buffer buffer);

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimension (int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void onDeath() {}

    public void kill() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean intersectsWith(StaticEntity other) {
        return getBounds().intersects(other.getBounds());
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
