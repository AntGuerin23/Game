package cegepst.engine.other;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.miscellaneous.other.Resource;
import cegepst.mainGame.worlds.World;

import java.awt.*;

public class Camera extends MovableEntity {

    private final Player player;
    private World currentWorld;
    private final Image respawnText;

    public Camera(Player player, World currentWorld) {
        EntityRepository.getInstance().registerEntity(this, false);
        this.player = player;
        this.currentWorld = currentWorld;
        respawnText = ResourceLoader.loadSprite(Resource.RESPAWN_TEXT.getPath());
        setDimension(800, 600);
    }

    @Override
    public void update() {
        updateValues();
        stopAtBorders();
    }

    @Override
    public void draw(Buffer buffer) {
        drawText(buffer);
        player.drawStaticImages(buffer, this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void updateValues() {
        currentWorld = MainGame.getInstance().getCurrentWorld();
        x = player.getX() - (800 / 2) + player.getWidth() / 2;
        y = player.getY() - (600 / 2);
    }

    private void stopAtBorders() {
        if (x > currentWorld.getEndBorderX() - 800) {
            x = currentWorld.getEndBorderX() - 800;
        } else if (x < currentWorld.getStartBorderX()) {
            x = currentWorld.getStartBorderX();
        }
        if (y > currentWorld.getEndBorderY() - 600) {
            y = currentWorld.getEndBorderY() - 600;
        } else if (y < currentWorld.getStartBorderY()) {
            y = currentWorld.getStartBorderY();
        }
    }

    private void drawText(Buffer buffer) {
        buffer.drawText("Coins : " + player.getCoinCount(), x, y + 80, Color.WHITE);
        if (player.isDead()) {
            buffer.drawImage(respawnText, x + 155, y + 280);
        }
        if (GameSettings.debug) {
            buffer.drawText("FPS: " + GameTime.getCurrentFps(), x, y + 60, Color.WHITE);
        }
    }

}
