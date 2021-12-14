package cegepst.mainGame.entities.environment;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class OldMan extends StaticEntity {

    private Image sprite;

    public OldMan(int x, int y) {
        teleport(x,y);
        setDimension(54, 72);
        sprite = ResourceLoader.loadSprite(Resource.OLD_MAN.getPath());
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }
}
