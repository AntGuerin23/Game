package cegepst.mainGame.worlds;

import cegepst.engine.ResourceLoader;
import cegepst.mainGame.gameComponents.Blockade;
import cegepst.mainGame.miscellaneous.Resource;

import java.util.ArrayList;

public class IntroWorld extends World {

    private ArrayList<Blockade> worldBorders;

    public IntroWorld() {
        initializeBorders();
        setWorldBorders(worldBorders);
    }

    private void initializeBorders() {
        worldBorders = new ArrayList<>();
        Blockade floor = new Blockade();
        floor.teleport(0,570);
        floor.setDimension(800,30);

        Blockade wall = new Blockade();
        wall.teleport(300,0);
        wall.setDimension(20,600);
        worldBorders.add(floor);
        worldBorders.add(wall);
    }

}
