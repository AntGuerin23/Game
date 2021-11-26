package cegepst.mainGame.gameComponents;

import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Player extends ControllableEntity {
    public Player(MovementController controller) {
        super(controller);
        setSpeed(5);
        setDimension(20,20);
        teleport(20,100);
        setJumpForce(10);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.ORANGE);
        drawHitBox(buffer);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
    }
}
