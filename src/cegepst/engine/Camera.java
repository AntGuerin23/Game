package cegepst.engine;

import cegepst.mainGame.entities.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.worlds.World;

public class Camera {

    private int x;
    private int y;
    private Player player;

    public Camera(Player player) {
        this.player = player;
    }

    public void update(World currentWorld) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
