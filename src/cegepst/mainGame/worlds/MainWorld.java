package cegepst.mainGame.worlds;

import cegepst.engine.WorldBuilder;
import cegepst.engine.other.Camera;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.environment.CrackedBlock;
import cegepst.mainGame.entities.environment.Crate;
import cegepst.mainGame.entities.environment.Door;
import cegepst.mainGame.entities.items.coin.Coin;
import cegepst.mainGame.entities.items.coin.CoinBag;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

public class MainWorld extends World {

    private static MainWorld instance;
    private boolean hasBeenInitialized = false;
    private Player player;

    public static MainWorld getInstance() {
        if (instance == null) {
            instance = new MainWorld();
        }
        return instance;
    }

    public void initialize (Player player, Camera camera) {
        if (!hasBeenInitialized) {
            initializeContent(player);
            hasBeenInitialized = true;
        }
        Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.MAIN_MUSIC.getPath()), -20, this);
        EntityRepository.getInstance().registerEntity(player, false);
        EntityRepository.getInstance().registerEntity(camera, false);
        player.teleport(getSpawnPointX(), getSpawnPointY());
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    @Override
    public int getSpawnPointX() {
        return 30;
    }

    @Override
    public int getSpawnPointY() {
        return 990;
    }

    private void initializeContent(Player player) {
        this.player = player;
        (new WorldBuilder()).buildWorldFromJSON(Resource.TEST_WORLD_JSON_PATH, player);
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        setBackground(Resource.TEST_WORLD_IMG_PATH);
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1440;
        super.endBorderY = 1440;
    }

    private void initializeEntities() {
        new Door(340,1185, ShopWorld.getInstance());
        new CrackedBlock(1200,1200);
        new CrackedBlock(1248,1200);
        new CrackedBlock(1296,1200);
        new CrackedBlock(1200,1152);
        new CrackedBlock(1248,1152);
        new CrackedBlock(1296,1152);
        new Coin(0,1008,player);
        new Coin(100,1008,player);
        new Coin(200,1008,player);
        new Coin(250,1008,player);
        new CoinBag(150,1000,player,5);
        new Crate(800,1200,player);
        new Crate(900,1200,player);
    }

    private MainWorld() {}

}