package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.entities.UpdatableEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Coin extends MovableEntity {

    private Player player;

    public Coin(int x, int y, Player player) {
        this.player = player;
        teleport(x,y);
        setDimension(8,10);
        setGravitating(false);
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        super.update();
        checkIfPickedUp();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawOval((x - width / 2) + 1,(y - height / 2),width + 4, height + 5, Color.YELLOW);
        buffer.drawRectangle(x + 1, y,4, 8, new Color(255, 195, 0));
    }

    private void checkIfPickedUp() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Player");
        if (intersectingEntity != null) {
            //TODO : Sound effect and others
            player.foundCoin(1);
            super.isDead = true;
        }
    }
}
