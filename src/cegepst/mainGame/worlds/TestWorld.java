package cegepst.mainGame.worlds;

import cegepst.engine.other.Camera;
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
    public void initialize(Player player, Camera camera) {
        if (!hasBeenInitialized) {
            initializeContent(player);
        }
        EntityRepository.getInstance().registerEntity(player, false);
        EntityRepository.getInstance().registerEntity(camera, false);
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
        player.teleport(500,500);
        hasBeenInitialized = true;
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1500;
        super.endBorderY = 1500;
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    private void initializeEntities() {
        new Door(340,1000,MainWorld.getInstance());
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
