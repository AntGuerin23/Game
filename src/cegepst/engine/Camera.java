package cegepst.engine;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.GameSettings;
import cegepst.mainGame.worlds.World;

import java.awt.*;

public class Camera extends MovableEntity {

    private Player player;
    private World currentWorld;

    public Camera(Player player, World currentWorld) {
        EntityRepository.getInstance().registerEntity(this,false);
        this.player = player;
        this.currentWorld = currentWorld;
        setDimension(800, 600);
    }

    @Override
    public void update() {
        x = player.getX() - (800 / 2) + player.getWidth() / 2;
        y = player.getY() - (600 / 2);

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

    @Override
    public void draw(Buffer buffer) {
        buffer.drawText("Coins : " + player.getCoinCount(),x + 730,y + 20, Color.WHITE);
        buffer.drawText("HP : " + player.getHp(),x + 730,y + 40, Color.WHITE);
        if (GameSettings.debug) {
            buffer.drawText("FPS: " + GameTime.getCurrentFps(), x + 10, y + 20, Color.WHITE);
            buffer.drawText(GameTime.getElapsedFormattedTime(), x + 10, y + 40, Color.WHITE);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
