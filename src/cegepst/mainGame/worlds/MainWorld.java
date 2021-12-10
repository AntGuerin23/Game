package cegepst.mainGame.worlds;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.mapCollisions.CollisionParser;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.mapCollisions.Blockade;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.enemies.Bouncer;
import cegepst.mainGame.entities.items.Coin;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.util.ArrayList;


public class MainWorld extends World {

    private Player player;
    private ArrayList<Blockade> blockades; //TODO: Delete when changing worlds
    private Bouncer enemy;

    public MainWorld(Player player) {
        this.player = player;
        blockades = (new CollisionParser()).createBlockades(Resource.TEST_WORLD_JSON_PATH);
        initializeEntities();
        initializeBorderLocations();
        instantiateBorders();
        setBackground(Resource.TEST_WORLD_IMG_PATH);
        Sound.playLoop(ResourceLoader.loadSound(Resource.MAIN_MUSIC.getPath()), -20);
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    @Override
    protected void drawEntities(Buffer buffer) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            entity.draw(buffer);
        }
    }

    @Override
    public int getSpawnPointX() {
        return 30;
    }

    @Override
    public int getSpawnPointY() {
        return 990;
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1440;
        super.endBorderY = 1440;
    }

    private void initializeEntities() {
        enemy = new Bouncer(300, player);
        enemy.teleport(100, 540);
        new Coin(0,1008,player);
        new Coin(50,1008,player);
        new Coin(100,1008,player);
        new Coin(150,1008,player);
        new Coin(200,1008,player);
        new Coin(250,1008,player);
    }
}