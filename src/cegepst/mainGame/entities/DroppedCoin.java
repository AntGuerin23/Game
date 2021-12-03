package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.Randomizer;

import java.awt.*;

public class DroppedCoin extends Coin {

    private Direction direction;
    private Player player;

    public DroppedCoin(int x, int y, Player player) {
        super(x, y, player);
        this.player = player;
        setGravitating(true);
        initializeValues(x, y);
    }

    @Override
    public void update() {
        super.update();
        scatter();
    }

    private void initializeValues(int x, int y) {
        setSpeed(Randomizer.randomInt(10, 50) / (double) 10);
        setHorizontalDirection(Direction.LEFT);
        direction = (Randomizer.randomInt(0,1) == 0) ? Direction.LEFT : Direction.RIGHT;
    }

    private void scatter() {
        moveHorizontally(direction);
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - 0.08);
        }
    }
}
