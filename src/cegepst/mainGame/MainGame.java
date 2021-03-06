package cegepst.mainGame;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.graphics.RenderingEngine;
import cegepst.engine.other.Camera;
import cegepst.engine.other.Game;
import cegepst.engine.repositories.EntityRepository;
import cegepst.mainGame.entities.environment.Door;
import cegepst.mainGame.entities.items.coin.CoinRespawner;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.worlds.MainWorld;
import cegepst.mainGame.worlds.World;

import java.util.ArrayList;
import java.util.Map;

public class MainGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World currentWorld;
    private Camera camera;
    private static MainGame instance;

    public static MainGame getInstance() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    @Override
    public void initialize() {
        instantiate();
    }

    @Override
    public void update() {
        manageInputs();
        checkForDoors();
        updateEntities();
        removeCorpses();
    }

    @Override
    public void draw(Buffer buffer) {
        drawWorld(buffer, camera);
    }

    @Override
    public void conclude() {}

    public World getCurrentWorld() {
        return currentWorld;
    }

    public boolean isPlayerNear(StaticEntity other) {
        return player.isNear(other);
    }

    private void instantiate() {
        instance = this;
        currentWorld = MainWorld.getInstance();
        gamePad = new GamePad();
        player = Player.getInstance(gamePad);
        camera = new Camera(player, currentWorld);
        currentWorld.initialize(player, camera);
    }

    private void manageInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isDebugPressed()) {
            GameSettings.debug = !GameSettings.debug;
        }
        if (gamePad.isFullScreenPressed()) {
            RenderingEngine.getInstance().getScreen().toggleFullscreen();
        }
        if (gamePad.isRespawnPressed() && player.isDead() && CoinRespawner.getInstance().isRespawnReady()) {
            player.respawn();
            player.teleport(currentWorld.getSpawnPointX(),currentWorld.getSpawnPointY());
        }
        if (gamePad.isGodModePressed()) {
            player.godMode();
        }
    }

    private void updateEntities() {
        CoinRespawner.getInstance().update();
        EntityRepository.getInstance().emptyCreationBuffer();
        for (Map.Entry<StaticEntity, World> entry : EntityRepository.getInstance().getRepository()) {
            StaticEntity entity = entry.getKey();
            if (entity instanceof UpdatableEntity && entry.getValue() == currentWorld && (MainGame.getInstance().isPlayerNear(entity) || entity instanceof Camera)) {
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
        for (Map.Entry<StaticEntity, World> entry : EntityRepository.getInstance().getRepository()) {
            StaticEntity entity = entry.getKey();
            if (entity.isDead()) {
                deadEntities.add(entity);
            }
        }
    }

    private void unregisterCadavers(ArrayList<StaticEntity> deadEntities) {
        for (StaticEntity deadEntity : deadEntities) {
            deadEntity.onDeath();
            EntityRepository.getInstance().unregisterEntity(deadEntity);
        }
    }

    private void checkForDoors() {
        if (gamePad.isUpPressed()) {
            Door door = player.isTouchingDoor();
            if (door != null) {
                currentWorld = door.getNewWorld();
                currentWorld.initialize(player, camera);
            }
        }
    }

    private void drawWorld(Buffer buffer, Camera camera) {
        currentWorld.draw(buffer, camera);
    }
}
