package cegepst.mainGame.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Roamer extends Enemy {

    private final int roamDistance;
    private int startingX;
    private int endingX;
    private Direction direction;

    public Roamer(int roamDistance) {
        this.roamDistance = roamDistance;
        initializeSuperValues();
        initializeVariables();
    }

    @Override
    public void update() {
        super.update();
        roam();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x - 2, y - 22, width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x, y - 20, width, 10, Color.RED);
        buffer.drawRectangle(x, y - 20, width / 3 * super.hp, 10, Color.GREEN);
        buffer.drawRectangle(x, y, width, height, Color.BLUE);
    }

    private void initializeVariables() {
        startingX = super.x;
        endingX = super.x + roamDistance;
        direction = Direction.RIGHT;
    }

    private void initializeSuperValues() {
        setMaxHp(3);
        setDimension(60, 60);
        teleport(100, 540);
        setSpeed(3);
        setStoredCoins(10);
    }

    private void roam() {
        if (super.x >= endingX) {
            direction = Direction.LEFT;
        }
        if (super.x <= startingX) {
            direction = Direction.RIGHT;
        }
        moveHorizontally(direction);
    }
}
