package cegepst.mainGame.entities.objects;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Resource;
import cegepst.mainGame.worlds.World;

import java.awt.*;

public class Door extends StaticEntity {

    private World newWorld;
    private Image sprite;


    public Door(int x, int y, World newWorld) {
        teleport(x,y);
        setDimension(71, 113);
        this.newWorld = newWorld;
        sprite = ResourceLoader.loadSprite(Resource.DOOR.getPath());
        EntityRepository.getInstance().registerEntity(this,false);
    }

    public World getNewWorld() {
        return newWorld;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite,x,y);
    }

    @Override
    public String toString() {
        return "Door";
    }
}
