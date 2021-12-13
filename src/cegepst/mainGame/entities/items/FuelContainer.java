package cegepst.mainGame.entities.items;

import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class FuelContainer extends UpdatableEntity {

    private static final int RESPAWN_DELAY = 300;
    private Image sprite;
    private int delay;
    private boolean isPickable;

    public FuelContainer(int x, int y) {
        teleport(x, y);
        setDimension(38,42);
        sprite = ResourceLoader.loadSprite(Resource.FUEL_SPRITE.getPath());
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        if (!isPickable) {
            respawn();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (isPickable) {
            buffer.drawImage(sprite, x, y);
        }
    }

    @Override
    public String toString() {
        return "FuelContainer";
    }

    public void pickUp() {
        isPickable = false;
        delay = RESPAWN_DELAY;
    }

    public boolean isPickable() {
        return isPickable;
    }

    private void respawn() {
        delay--;
        if (delay <= 0) {
            isPickable = true;
        }
    }
}
