package cegepst.mainGame;

import cegepst.engine.Game;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.gameComponents.GamePad;
import cegepst.mainGame.gameComponents.Player;

import java.util.ArrayList;

public class MainGame extends Game {

    private GamePad gamePad;
    private Player player;
    private ArrayList<UpdatableEntity> updatableEntities;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        updatableEntities = new ArrayList<>();
        updatableEntities.add(player);
    }

    @Override
    public void update() {
        if (gamePad.isQuitPushed()) {
            stop();
        }
        for (UpdatableEntity entity : updatableEntities) {
            entity.update();
        }
        player.update();
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
    }

    @Override
    public void conclude() {

    }
}
