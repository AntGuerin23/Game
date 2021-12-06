package cegepst.mainGame.worlds;

import cegepst.engine.EntityRepository;
import cegepst.engine.CollisionParser;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.Blockade;
import cegepst.mainGame.entities.Bouncer;
import cegepst.mainGame.entities.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.util.ArrayList;


public class TestWorld extends World {

    private Player player;
    private ArrayList<Blockade> blockades;
    private Bouncer enemy;

    public TestWorld(Player player) {
        this.player = player;
        initializeBorderLocations();
        instantiateBorders();
        blockades = (new CollisionParser()).createBlockades(Resource.TEST_WORLD_JSON_PATH);
        setBackground(Resource.TEST_WORLD_IMG_PATH);
        enemy = new Bouncer(300);
        enemy.teleport(100, 540);
    }

    @Override
    protected void drawEntities(Buffer buffer) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            entity.draw(buffer);
        }
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1440;
        super.endBorderY = 1440;
    }

    private void instantiateBorders() {
        super.createBorders();
    }
}
