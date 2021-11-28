package cegepst.mainGame.worlds;

import cegepst.engine.ResourceLoader;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public abstract class World {

    private Image background;

    public void draw(Buffer buffer) {
        if (background != null) {
            buffer.drawImage(background,0,-64);
        }
    }

    protected void setBackground(Resource resource) {
        background = ResourceLoader.loadSprite(resource);
    }



}
