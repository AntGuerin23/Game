package cegepst.mainGame.worlds;

import cegepst.mainGame.entities.Blockade;

public class IntroWorld extends World {

    public IntroWorld() {
        initializeBorders();
    }

    private void initializeBorders() {
        Blockade floor = new Blockade();
        floor.teleport(0,570);
        floor.setDimension(800,30);

        Blockade obstacle = new Blockade();
        obstacle.teleport(150,430);
        obstacle.setDimension(100,30);

        Blockade wall = new Blockade();
        wall.teleport(300,0);
        wall.setDimension(20,600);
    }

}
