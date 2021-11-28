package cegepst.mainGame;

import cegepst.engine.EntityRepository;
import cegepst.engine.Game;
import cegepst.engine.GameTime;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.Bullet;
import cegepst.mainGame.entities.Coin;
import cegepst.mainGame.entities.Enemy;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.entities.Player;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.worlds.IntroWorld;
import com.sun.imageio.stream.CloseableDisposerRecord;

import java.awt.*;
import java.util.ArrayList;

public class MainGame extends Game {

    private GamePad gamePad;
    private Player player;
    private IntroWorld introWorld;
    private Enemy enemy;

    @Override
    public void initialize() {
        instantiate();
    }

    @Override
    public void update() {
        manageInputs();
        updateEntities();
        unregisterCorpses();
    }

    @Override
    public void draw(Buffer buffer) {
        drawEntities(buffer);
        drawMiscellaneous(buffer);
        if (GameSettings.debug) {
            drawFPS(buffer);
        }
    }

    @Override
    public void conclude() {

    }

    private void instantiate() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        introWorld = new IntroWorld();
        enemy = new Enemy();
        new Coin(155,410, player);
        new Coin(170,410, player);
        new Coin(185,410, player);
        new Coin(200,410, player);
        new Coin(215,410, player);
        new Coin(230,410, player);
    }

    private void manageInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isJumpPressed()) {
            player.jump();
        }
        if (gamePad.isDebugPressed()) {
            GameSettings.debug = !GameSettings.debug;
        }
        if (gamePad.isFirePressed()) {
            new Bullet(player);
        }
    }

    private void updateEntities() {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            if (entity instanceof UpdatableEntity) {
                ((UpdatableEntity) entity).update();
            }
        }
    }

    private void unregisterCorpses() {
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
            EntityRepository.getInstance().unregisterEntity(deadEntity);
        }
    }

    private void drawEntities(Buffer buffer) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            entity.draw(buffer);
        }
    }

    private void drawMiscellaneous(Buffer buffer) {
        introWorld.draw(buffer);
        buffer.drawText("Coins : " + player.getCoinCount(),730,20, Color.WHITE);
    }

    private void drawFPS(Buffer buffer) {
        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 40, Color.WHITE);
    }
}
