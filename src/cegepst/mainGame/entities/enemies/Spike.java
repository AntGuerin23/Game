package cegepst.mainGame.entities.enemies;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Spike extends StaticEntity {

    private Image sprite;

    public Spike(int x, int y, Direction spikeDirection) {
        setDimension(48,48);
        teleport(x,y);
        sprite = ResourceLoader.loadSprite(getSpritePath(spikeDirection));
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite,x,y);
    }

    @Override
    public String toString() {
        return "Spike";
    }

    private String getSpritePath(Direction spikeDirection) {
        if (spikeDirection == Direction.UP) {
            return Resource.SPIKE_UP.getPath();
        }
//        if (spikeDirection == Direction.RIGHT) {
//            return Resource.SPIKE_RIGHT.getPath();
//        }
//        if (spikeDirection == Direction.DOWN) {
//            return Resource.SPIKE_DOWN.getPath();
//        }
//        return SPIKE_LEFT.getPath();
        return null;
    }
}
