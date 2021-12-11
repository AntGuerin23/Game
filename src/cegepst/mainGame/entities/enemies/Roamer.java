package cegepst.mainGame.entities.enemies;

import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public abstract class Roamer extends Enemy {

    private int roamDistance;
    private int startingX;
    private int endingX;
    private Direction direction;

    public Roamer(int x, int y, int roamDistance, Player player) {
        super(player);
        initializeVariables(x, y, roamDistance);
        setGravitating(true);
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

    private void initializeVariables(int x, int y, int roamDistance) {
        this.x = x;
        this.y = y;
        this.roamDistance = roamDistance;
        startingX = x;
        endingX = x + roamDistance;
        direction = Direction.RIGHT;
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
