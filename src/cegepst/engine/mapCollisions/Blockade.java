package cegepst.engine.mapCollisions;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.GameSettings;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        EntityRepository.getInstance().registerEntity(this, true);
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.debug) {
            buffer.drawRectangle(x,y,width,height,new Color(255, 0, 0, 100));
        }
    }
}
