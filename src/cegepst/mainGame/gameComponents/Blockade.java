package cegepst.mainGame.gameComponents;

import cegepst.engine.CollidableRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.GameSettings;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.debug) {
            buffer.drawRectangle(x,y,width,height,new Color(255, 0, 0, 100));
        }
    }
}
