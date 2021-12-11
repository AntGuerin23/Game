package cegepst.mainGame.worlds;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.mapCollisions.Blockade;
import cegepst.mainGame.entities.items.coin.Coin;
import cegepst.mainGame.entities.player.Player;

public class IntroWorld extends World {

    private Player player;

    public IntroWorld(Player player) {
        this.player = player;
        initializeBorderLocations();
        instantiateBorders();
        initializeCustomBlockades();
    }

    @Override
    protected void drawEntities(Buffer buffer) {

    }

    @Override
    public int getSpawnPointX() {
        return 0;
    }

    @Override
    public int getSpawnPointY() {
        return 0;
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
        blockade.teleport(100,470);

        new Coin(155,410, player);
        new Coin(170,410, player);
        new Coin(185,410, player);
        new Coin(200,410, player);
        new Coin(215,410, player);
        new Coin(230,410, player);
    }
}
