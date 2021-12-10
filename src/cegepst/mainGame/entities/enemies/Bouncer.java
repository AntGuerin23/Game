package cegepst.mainGame.entities.enemies;

import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public class Bouncer extends Roamer {

    private int jumpCooldown = 50;

    public Bouncer(int roamDistance, Player player) {
        super(roamDistance, player);
        initializeDefaultValues();
        setGravitating(true);
        setJumpForce(10);
    }

    @Override
    public void update() {
        super.update();
        if (jumpCooldown == 0) {
            jump();
            jumpCooldown = 50;
        }
        jumpCooldown--;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x - 2, y - 22, width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x, y - 20, width, 10, Color.RED);
        buffer.drawRectangle(x, y - 20, width / maxHp * super.hp, 10, Color.GREEN);
        buffer.drawRectangle(x, y, width, height, Color.BLUE);
    }

    private void initializeDefaultValues() {
        setMaxHp(5);
        setDimension(60, 60);
        setSpeed(3);
        setStoredCoins(10);
    }
}
