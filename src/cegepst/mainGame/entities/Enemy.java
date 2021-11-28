package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.entities.GravitatingEntity;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public class Enemy extends GravitatingEntity {

    private static final int MAX_HP = 3;
    private int hp;

    public Enemy() {
        hp = MAX_HP;
        setDimension(60,60);
        teleport(50,100);
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        super.update();
        checkIfDead();
        checkIntersection();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x - 2,y - 22,width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x,y - 20,width,10,Color.RED);
        buffer.drawRectangle(x,y - 20,width / 3 * hp,10,Color.GREEN);
        buffer.drawRectangle(x,y,width,height, Color.BLUE);
    }

    private void checkIntersection() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, Bullet.class.toString());
        if (intersectingEntity instanceof Bullet) {
            getHit(1);
            intersectingEntity.kill();
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
}
