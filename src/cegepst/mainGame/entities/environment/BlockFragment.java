package cegepst.mainGame.entities.environment;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Randomizer;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class BlockFragment extends MovableEntity {

    private static final int DESPAWN_DELAY = 60;
    private int delay;
    private Direction direction;
    private Image sprite;

    public BlockFragment(int x, int y, Direction bulletDirection) {
        EntityRepository.getInstance().registerEntityBuffered(this);
        setDimension(4,4);
        initializeValues(x, y, bulletDirection);
    }

    @Override
    public void update() {
        super.update();
        scatter();
        despawn();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }

    private void initializeValues(int x, int y, Direction bulletDirection) {
        setGravitating(true);
        setSpeed(Randomizer.randomInt(15, 50) / (double) 10);
        delay = DESPAWN_DELAY;
        sprite = ResourceLoader.loadSprite(Resource.BLOCK_FRAGMENT.getPath());
        gravityForce = 0.5;
        this.direction = bulletDirection.revert();
        teleport(x,y + 20);
    }

    private void scatter() {
        moveHorizontally(direction);
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - 0.08);
        }
    }

    private void despawn() {
        delay--;
        if (delay <= 0) {
            isDead = true;
        }
    }
}
