package cegepst.mainGame.worlds;

import cegepst.engine.repositories.EntityRepository;
import cegepst.mainGame.entities.Door;
import cegepst.mainGame.entities.player.Player;

public class TestWorld extends World {

    private Player player;
    private static TestWorld instance;
    private boolean hasBeenInitialized = false;

    public static TestWorld getInstance() {
        if (instance == null) {
            instance = new TestWorld();
        }
        return instance;
    }

    @Override
    public void initialize(Player player) {
        if (!hasBeenInitialized) {
            initializeContent(player);
        }
        EntityRepository.getInstance().registerEntity(player, false);
    }

    @Override
    public int getSpawnPointX() {
        return 50;
    }

    @Override
    public int getSpawnPointY() {
        return 50;
    }

    private void initializeContent(Player player) {
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        this.player = player;
        hasBeenInitialized = true;
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

    private void initializeEntities() {
        new Door(340,1000,MainWorld.getInstance());
//        Blockade blockade = new Blockade();
//        blockade.setDimension(650,30);
//        blockade.teleport(100,470);
//
//        new Coin(155,410, player);
//        new Coin(170,410, player);
//        new Coin(185,410, player);
//        new Coin(200,410, player);
//        new Coin(215,410, player);
//        new Coin(230,410, player);
    }

    private TestWorld() {}
}
