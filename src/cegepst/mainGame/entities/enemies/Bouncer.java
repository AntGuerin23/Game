package cegepst.mainGame.entities.enemies;

import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public abstract class Bouncer extends Roamer {

    private int jumpCooldown = 50;
    protected boolean canBounce;

    public Bouncer(int x, int y, int roamDistance, Player player, boolean canBounce) {
        super(x, y, roamDistance, player);
        initializeValues(canBounce);
        setGravitating(true);
        setJumpForce(10);
    }

    @Override
    public void update() {
        super.update();
        if (canBounce) {
            if (jumpCooldown == 0) {
                jump();
                jumpCooldown = 120;
            }
            jumpCooldown--;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x - 2, y - 22, width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x, y - 20, width, 10, Color.RED);
        buffer.drawRectangle(x, y - 20, width / maxHp * super.hp, 10, Color.GREEN);
        buffer.drawRectangle(x, y, width, height, Color.BLUE);
    }

    private void initializeValues(boolean canBounce) {
        this.canBounce = canBounce;
    }
}
