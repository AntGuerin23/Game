package cegepst.mainGame.worlds;

import cegepst.engine.WorldBuilder;
import cegepst.engine.other.Camera;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.objects.Crate;
import cegepst.mainGame.entities.objects.Door;
import cegepst.mainGame.entities.enemies.Bouncer;
import cegepst.mainGame.entities.enemies.Rat;
import cegepst.mainGame.entities.items.coin.Coin;
import cegepst.mainGame.entities.items.coin.CoinBag;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

public class MainWorld extends World {

    private Player player;
    private Bouncer enemy;
    private static MainWorld instance;
    private boolean hasBeenInitialized = false;

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
        Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.MAIN_MUSIC.getPath()), -20, this);
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1440;
        super.endBorderY = 1440;
    }

    private void initializeEntities() {
        new Door(340,1185,TestWorld.getInstance());
//        new Spike(340,1250, Direction.UP);
//        new Spike(390,1250, Direction.UP);
//        new Spike(440,1250, Direction.UP);
//        new Spike(490,1250, Direction.UP);
//        new Spike(540,1250, Direction.UP);
//        new Spike(590,1250, Direction.UP);
//        new Spike(640,1250, Direction.UP);
        enemy = new Rat(600, 1200, 600, player, true);
        new Coin(0,1008,player);
        new Coin(100,1008,player);
        new Coin(200,1008,player);
        new Coin(250,1008,player);
        new CoinBag(150,1000,player,5);
        new Crate(390,1200,player);
    }

    private MainWorld() {}

}