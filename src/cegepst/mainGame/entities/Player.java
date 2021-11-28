package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.GameSettings;

import java.awt.*;

public class Player extends ControllableEntity {

    private int coinCount;

    public Player(MovementController controller) {
        super(controller);
        EntityRepository.getInstance().registerEntity(this,false);
        initializeValues();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.ORANGE);
        if (hasMoved() && GameSettings.debug) {
            drawHitBox(buffer);
        }
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
    }

    public void foundCoin(int nbOfCoins) {
        coinCount += nbOfCoins;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void initializeValues() {
        coinCount = 0;
        setSpeed(5);
        setDimension(20,20);
        teleport(20,100);
        setJumpForce(10);
    }
}
