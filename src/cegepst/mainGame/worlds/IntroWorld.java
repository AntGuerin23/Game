package cegepst.mainGame.worlds;

import cegepst.mainGame.entities.Blockade;

public class IntroWorld extends World {

    public IntroWorld() {
        initializeBorderLocations();
        instantiateBorders();
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

}
