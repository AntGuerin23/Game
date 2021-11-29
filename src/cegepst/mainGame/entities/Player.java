package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.miscellaneous.other.GameSettings;

import java.awt.*;

public class Player extends ControllableEntity {

    private final static int MAX_HP = 3;
    private int coinCount;
    private int hp;

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
        checkIfDamaged();
        checkIfDead();
    }

    public void foundCoin(int nbOfCoins) {
        coinCount += nbOfCoins;
    }

    public int getCoinCount() {
        return coinCount;
    }

    @Override
    public String toString() {
        return "Player";
    }

    private void checkIfDamaged() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Enemy");
        if (intersectingEntity != null) {
            getHit(1);
        }
    }

    private void getHit(int damage) {
        hp -= damage;
    }

    private void checkIfDead() {
        if (hp == 0) {
            isDead = true;
        }
    }

    private void initializeValues() {
        coinCount = 0;
        hp = MAX_HP;
        setSpeed(5);
        setDimension(20,20);
        teleport(20,580);
        setJumpForce(10);
    }
}
