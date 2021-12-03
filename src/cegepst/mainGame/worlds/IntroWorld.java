package cegepst.mainGame.worlds;

import cegepst.mainGame.entities.Blockade;

public class IntroWorld extends World {

    public IntroWorld() {
        initializeBorderLocations();
        instantiateBorders();
        initializeCustomBlockades();

    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1000;
        super.endBorderY = 600;
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    private void initializeCustomBlockades() {
        Blockade blockade = new Blockade();
        blockade.setDimension(650,30);
        blockade.teleport(400,450);
    }

}
