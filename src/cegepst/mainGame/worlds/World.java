package cegepst.mainGame.worlds;

import cegepst.engine.ResourceLoader;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.gameComponents.Blockade;
import cegepst.mainGame.miscellaneous.Resource;
import jdk.nashorn.internal.ir.Block;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class World {

    private Image background;
    private ArrayList<Blockade> worldBorders;

    public void draw(Buffer buffer) {
        if (background != null) {
            buffer.drawImage(background,0,-64);
        }
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
    }

    protected void setWorldBorders(ArrayList<Blockade> worldBorders) {
        this.worldBorders = worldBorders;
    }

    protected void setBackground(Resource resource) {
        background = ResourceLoader.loadSprite(resource);
    }



}
