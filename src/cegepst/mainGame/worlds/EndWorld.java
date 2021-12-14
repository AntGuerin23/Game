package cegepst.mainGame.worlds;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.Camera;
import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public class EndWorld extends World {

    @Override
    public void initialize(Player player, Camera camera) {
        camera.teleport(0,0);
    }

    @Override
    public int getSpawnPointX() {
        return 0;
    }

    @Override
    public int getSpawnPointY() {
        return 0;
    }

    @Override
    public void draw(Buffer buffer, Camera camera) {
        super.draw(buffer, camera);
        buffer.drawText("You've escaped the cave !",270,270, Color.WHITE);
        buffer.drawText("Thank you for playing",290,370, Color.WHITE);
    }
}
