package cegepst.mainGame.entities.enemies;

import cegepst.engine.controls.Direction;
import cegepst.mainGame.entities.player.Player;

public abstract class Roamer extends Enemy {


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

    private void initializeVariables(int x, int y, int roamDistance) {
        this.x = x;
        this.y = y;
        this.roamDistance = roamDistance;
        startingX = x;
        endingX = x + roamDistance;
        direction = Direction.RIGHT;
    }

    private void roam() {
        if (roamDistance > 0) {
            if (super.x >= endingX) {
                direction = Direction.LEFT;
            }
            if (super.x <= startingX) {
                direction = Direction.RIGHT;
            }
            moveHorizontally(direction);
        }
    }
}
