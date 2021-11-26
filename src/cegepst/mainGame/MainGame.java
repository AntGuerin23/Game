package cegepst.mainGame;

import cegepst.engine.Game;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.gameComponents.GamePad;
import cegepst.mainGame.gameComponents.Player;
import cegepst.mainGame.worlds.IntroWorld;

import java.util.ArrayList;

public class MainGame extends Game {

    private GamePad gamePad;
    private Player player;
    private ArrayList<UpdatableEntity> updatableEntities;
    private IntroWorld introWorld;

    @Override
    public void initialize() {
        instantiate();
        groupUpdatables();
    }

    @Override
    public void update() {
        manageInputs();
        updateEntities();
    }

    @Override
    public void draw(Buffer buffer) {
        drawEntities(buffer);
        drawMiscellaneous(buffer);
    }

    @Override
    public void conclude() {

    }

    private void instantiate() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        updatableEntities = new ArrayList<>();
        introWorld = new IntroWorld();
    }

    private void groupUpdatables() {
        updatableEntities.add(player);
    }

    private void manageInputs() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        if (gamePad.isJumpPressed()) {
            player.jump();
        }
    }

    private void updateEntities() {
        for (UpdatableEntity entity : updatableEntities) {
            entity.update();
        }
    }

    private void drawEntities(Buffer buffer) {
        player.draw(buffer);
    }

    private void drawMiscellaneous(Buffer buffer) {
        introWorld.draw(buffer);
    }
}
