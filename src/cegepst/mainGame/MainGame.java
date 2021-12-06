package cegepst.mainGame;

import cegepst.engine.Camera;
import cegepst.engine.EntityRepository;
import cegepst.engine.Game;
import cegepst.engine.GameTime;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.mainGame.entities.*;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.worlds.TestWorld;
import cegepst.mainGame.worlds.World;

import java.awt.*;
import java.util.ArrayList;

public class MainGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World currentWorld;
    private Bouncer enemy;
    private Camera camera;

    @Override
    public void initialize() {
        instantiate();
    }

    @Override
    public void update() {
        manageInputs();
        updateEntities();
        removeCorpses();
    }

    @Override
    public void draw(Buffer buffer) {
        drawWorld(buffer, camera);
    }

    @Override
    public void conclude() {

    }

    private void instantiate() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(800,0);
        currentWorld = new TestWorld(player);
        camera = new Camera(player, currentWorld);
    }

    private void manageInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isDebugPressed()) {
            GameSettings.debug = !GameSettings.debug;
        }
        if (gamePad.isFirePressed()) {
            new Bullet(player);
        }
        if (gamePad.isFullScreenPressed()) {
            RenderingEngine.getInstance().getScreen().toggleFullscreen();
        }
    }

    private void updateEntities() {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            if (entity instanceof UpdatableEntity) {
                ((UpdatableEntity) entity).update();
            }
        }
    }

    private void removeCorpses() {
        ArrayList<StaticEntity> deadEntities = new ArrayList<>();
        tallyRemains(deadEntities);
        unregisterCadavers(deadEntities);
    }

    private void tallyRemains(ArrayList<StaticEntity> deadEntities) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            if (entity.isDead()) {
                deadEntities.add(entity);
            }
        }
    }

    private void unregisterCadavers(ArrayList<StaticEntity> deadEntities) {
        for (StaticEntity deadEntity : deadEntities) {
            dropCoins(deadEntity);
            EntityRepository.getInstance().unregisterEntity(deadEntity);
        }
    }

    private void dropCoins(StaticEntity deadEntity) {
        if (deadEntity instanceof Enemy) {
            for (int i = 0; i < ((Enemy) deadEntity).getStoredCoins(); i++) {
                new DroppedCoin(deadEntity.getX(),deadEntity.getY(), player);
            }
        }
    }

    private void drawWorld(Buffer buffer, Camera camera) {
        currentWorld.draw(buffer, camera);
    }


}
