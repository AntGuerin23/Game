package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.GravitatingEntity;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Bullet extends MovableEntity {

    private final Direction playerDirection;

    public Bullet(Player player) {
        setSpeed(10);
        setDimension(10,6);
        teleport(player.getX() + player.getWidth() / 2,player.getY() + (player.getHeight() / 2) - height / 2);
        playerDirection = player.getHorizontalDirection();
        EntityRepository.getInstance().registerEntity(this, false);
    }

    @Override
    public void update() {
        super.update();
        moveHorizontally(playerDirection);
        checkIfDead();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x,y,width,height, Color.YELLOW);
    }

    @Override
    public String toString() {
        return "Bullet";
    }

    private void checkIfDead() {
        //TODO : Check if collided with enemy
        if (!hasMoved()) {
            super.isDead = true;
        }
    }
}
