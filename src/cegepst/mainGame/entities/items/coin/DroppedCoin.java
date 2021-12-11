package cegepst.mainGame.entities.items.coin;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.other.IntersectionChecker;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Randomizer;

public class DroppedCoin extends Coin {

    private Direction direction;
    private Player player;

    public DroppedCoin(int x, int y, Player player) {
        super(x, y, player);
        this.player = player;
        setGravitating(true);
        initializeValues();
    }

    @Override
    public void update() {
        super.update();
        scatter();
        checkForSpikeHit();

    }

    private void initializeValues() {
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

    private void checkForSpikeHit() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Spike");
        if (intersectingEntity != null) {
            CoinRespawner.getInstance().notifyAboutCoinDeath();
            isDead = true;
        }
    }
}
