package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

import javax.sound.sampled.AudioInputStream;
import java.awt.*;

public class Bullet extends MovableEntity {

    private final Direction playerDirection;


    public Bullet(Player player) {
        Sound.playOnce(ResourceLoader.loadSound(Resource.GUN_SOUND_EFFECT.getPath()), -30);
        setSpeed(25);
        setDimension(10,6);
        playerDirection = player.getHorizontalDirection();
        int xOffset = (playerDirection == Direction.LEFT) ? -39 : 30;
        teleport(player.getX() + xOffset + player.getWidth() / 2,player.getY() + 3 + (player.getHeight() / 2) - height / 2);
        setGravitating(false);
        //EntityRepository.getInstance().registerEntity(this, false);
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

    @Override
    protected void checkIfHasMoved() {
        moved = (x != lastX || y != lastY);
        lastX = x;
        lastY = y;
    }

    private void checkIfDead() {
        if (!hasMoved()) {
            super.isDead = true;
        }
    }
}
