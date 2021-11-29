package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.GravitatingEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.Randomizer;

import java.awt.*;

public class DroppedCoin extends GravitatingEntity {

    private Direction direction;
    private Player player;

    public DroppedCoin(int x, int y, Player player) {
        this.player = player;
        EntityRepository.getInstance().registerEntity(this,false);
        initializeValues(x, y);
    }

    @Override
    public void update() {
        super.update();
        scatter();
        checkIfPickedUp();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawOval((x - width / 2) + 1,(y - height / 2),width + 4, height + 5, Color.YELLOW);
        buffer.drawRectangle(x + 1, y,4, 8, new Color(255, 195, 0));
    }

    private void initializeValues(int x, int y) {
        setJumpForce(2);
        teleport(x, y);
        setDimension(8,10);
        setSpeed(Randomizer.randomInt(10, 50) / (double) 10);
        setHorizontalDirection(Direction.LEFT);
        direction = (Randomizer.randomInt(0,1) == 0) ? Direction.LEFT : Direction.RIGHT;
    }

    private void scatter() {
        moveHorizontally(direction);
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - 0.1);
        }
    }

    private void checkIfPickedUp() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Player");
        if (intersectingEntity != null) {
            //TODO : Sound effect and others
            player.foundCoin(1);
            super.isDead = true;
        }
    }
}
