package cegepst.mainGame.worlds;

import cegepst.engine.WorldBuilder;
import cegepst.engine.other.Camera;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.environment.Door;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

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
            setBackground(Resource.TEST_WORLD_100);
        }
        EntityRepository.getInstance().registerEntity(player, false);
        EntityRepository.getInstance().registerEntity(camera, false);
        player.teleport(getSpawnPointX(),getSpawnPointY());
    }

    @Override
    public int getSpawnPointX() {
        return 200;
    }

    @Override
    public int getSpawnPointY() {
        return 200;
    }

    private void initializeContent(Player player) {
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        this.player = player;
        (new WorldBuilder()).buildWorldFromJSON(Resource.TEST_COIN_WORLD_JSON_PATH, player);
        player.teleport(getSpawnPointX(),getSpawnPointY());
        Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.SHOP_MUSIC.getPath()), -20, this);
        hasBeenInitialized = true;
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 4800;
        super.endBorderY = 4800;
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
