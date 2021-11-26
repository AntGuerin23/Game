package cegepst.engine.controls;

public enum Direction {

    LEFT(1,-1, 0),
    RIGHT(2,1, 0),
    UP(3,0, -1),
    DOWN(4,0, 1);

    private final int animationID;
    private final int xMultiplier;
    private final int yMultiplier;

    Direction(int animationID, int xMultiplier, int yMultiplier) {
        this.animationID = animationID;
        this.xMultiplier = xMultiplier;
        this.yMultiplier = yMultiplier;
    }

    public int getAnimationID() {
        return animationID;
    }

    public int getVelocityX(int speed) {
        return xMultiplier * speed;
    }

    public int getVelocityY(int speed) {
        return yMultiplier * speed;
    }

    public int getYMultiplier() {
        return yMultiplier;
    }
}
