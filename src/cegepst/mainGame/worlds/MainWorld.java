package cegepst.mainGame.worlds;

import cegepst.engine.WorldBuilder;
import cegepst.engine.other.Camera;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.enemies.Rat;
import cegepst.mainGame.entities.environment.Crate;
import cegepst.mainGame.entities.environment.Door;
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
        player.teleport(getSpawnPointX(), getSpawnPointY());
        if (!hasBeenInitialized) {
            initializeContent(player);
            hasBeenInitialized = true;
            //player.teleport(6167, 8306);
        }
        reset(camera);
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    @Override
    public int getSpawnPointX() {
        return 9605;
    }

    @Override
    public int getSpawnPointY() {
        return 9600;
    }

    private void initializeContent(Player player) {
        this.player = player;
        (new WorldBuilder()).buildWorldFromJSON(Resource.MAIN_WORLD_JSON, player);
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        setBackground(Resource.MAIN_WORLD);
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 19200;
        super.endBorderY = 19200;
    }

    private void initializeEntities() {
        new Door(9590,9580, ShopWorld.getInstance());
        new Crate(11500,12650,player);
        new Rat(9555,12818,470,player,false,0);
        new Rat(8900,12818,455,player,false,0);
        new Crate(6536, 12818, player);
        new Crate(6751, 12818, player);
        new CoinBag(6578, 9938, player, 15);
        new CoinBag(12181, 7202, player, 25);
        new CoinBag(12342, 7824, player, 15);
        new CoinBag(3984, 8306, player, 25);
        new Rat(7432, 8306,730,player,false,5);
        new Rat(6538, 8354,600,player,true,10);
        new Rat(6638, 8354,500,player,true,10);
        new Rat(3984,8306,650,player,false,0);
        new Rat(4000,8306,650,player,false,0);
        new Rat(4100,8306,550,player,false,0);
        new Rat(4200,8306,450,player,false,0);
        new Rat(4300,8306,350,player,false,0);
    }

    private void reset(Camera camera) {
        Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.MAIN_MUSIC.getPath()), -20, this);
        EntityRepository.getInstance().registerEntity(player, false);
        EntityRepository.getInstance().registerEntity(camera, false);
    }

    private MainWorld() {}

}