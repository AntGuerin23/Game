package cegepst.mainGame.entities;

import cegepst.engine.EntityRepository;
import cegepst.engine.IntersectionChecker;
import cegepst.engine.entities.GravitatingEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;

import java.awt.*;

public abstract class Enemy extends GravitatingEntity {

    protected int hp;
    protected int storedCoins;

    @Override
    public abstract void draw(Buffer buffer);

    public Enemy() {
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        super.update();
        checkIfDead();
        checkIfShot();
    }

    public int getStoredCoins() {
        return storedCoins;
    }

    protected void setMaxHp(int maxHp) {
        hp = maxHp;
    }

    protected void setStoredCoins(int storedCoins) {
        this.storedCoins = storedCoins;
    }


    private void checkIfShot() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Bullet");
        if (intersectingEntity != null) {
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

    @Override
    public String toString() {
        return "Enemy";
    }
}
