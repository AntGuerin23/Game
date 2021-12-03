package cegepst.mainGame.entities;

import cegepst.engine.Sound;
import cegepst.engine.controls.Direction;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Bouncer extends Roamer {

    public Bouncer(int roamDistance) {
        super(roamDistance);
        initializeDefaultValues();
        setGravitating(true);
        setJumpForce(10);
    }

    @Override
    public void update() {
        super.update();
        jump();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x - 2, y - 22, width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x, y - 20, width, 10, Color.RED);
        buffer.drawRectangle(x, y - 20, width / 3 * super.hp, 10, Color.GREEN);
        buffer.drawRectangle(x, y, width, height, Color.BLUE);
    }

    private void initializeDefaultValues() {
        setMaxHp(3);
        setDimension(60, 60);
        setSpeed(3);
        setStoredCoins(10);
    }
}
