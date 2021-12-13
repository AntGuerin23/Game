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

    private Direction direction;
    private Image sprite;

    public BlockFragment(int x, int y) {
        EntityRepository.getInstance().registerEntityBuffered(this);
        setDimension(4,4);
        initializeValues();
        this.direction = (Randomizer.randomInt(0, 1) == 0) ? Direction.LEFT : Direction.RIGHT;
        teleport(x + ((this.direction == Direction.LEFT) ? -3 : 51) ,y + 20);
        sprite = ResourceLoader.loadSprite(Resource.BLOCK_FRAGMENT.getPath());
        gravityForce = 0.5;
    }

    @Override
    public void update() {
        super.update();
        scatter();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }

    private void initializeValues() {
        setGravitating(true);
        setSpeed(Randomizer.randomInt(15, 50) / (double) 10);
    }

    private void scatter() {
        moveHorizontally(direction);
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - 0.08);
        }
    }
}
