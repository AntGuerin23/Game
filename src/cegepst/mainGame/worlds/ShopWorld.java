package cegepst.mainGame.worlds;

import cegepst.engine.WorldBuilder;
import cegepst.engine.other.Camera;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.engine.resources.SoundStopper;
import cegepst.mainGame.entities.environment.BuyStation;
import cegepst.mainGame.entities.environment.Door;
import cegepst.mainGame.entities.environment.ShopItem;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

public class ShopWorld extends World implements SoundStopper {

    private Player player;
    private static ShopWorld instance;
    private boolean hasBeenInitialized = false;

    public static ShopWorld getInstance() {
        if (instance == null) {
            instance = new ShopWorld();
        }
        return instance;
    }

    @Override
    public void initialize(Player player, Camera camera) {
        if (!hasBeenInitialized) {
            initializeContent(player);
            setBackground(Resource.SHOP);
        }
        reset(camera);
    }

    @Override
    public int getSpawnPointX() {
        return 355;
    }

    @Override
    public int getSpawnPointY() {
        return 750;
    }

    private void initializeContent(Player player) {
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        this.player = player;
        (new WorldBuilder()).buildWorldFromJSON(Resource.SHOP_JSON, player);
        player.teleport(getSpawnPointX(),getSpawnPointY());
        hasBeenInitialized = true;
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1536;
        super.endBorderY = 1152;
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    private void initializeEntities() {
        new Door(340,650,MainWorld.getInstance());
        new BuyStation(815,700, ShopItem.SHOTGUN);
        new BuyStation(917,680, ShopItem.JETPACK);
        new BuyStation(1015,673, ShopItem.GLOVES);
    }

    private void reset(Camera camera) {
        Sound.playStoppableLoop(ResourceLoader.loadSound(Resource.SHOP_MUSIC.getPath()), -20, this);
        EntityRepository.getInstance().registerEntity(player, false);
        EntityRepository.getInstance().registerEntity(camera, false);
        player.teleport(getSpawnPointX(),getSpawnPointY());
    }

    private ShopWorld() {}
}
