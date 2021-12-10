package cegepst.mainGame.entities.enemies;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.resources.Sound;
import cegepst.mainGame.entities.items.DroppedCoin;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

public abstract class Enemy extends MovableEntity {

    protected int hp;
    protected int maxHp;
    protected int storedCoins;
    private Player player;

    @Override
    public abstract void draw(Buffer buffer);

    public Enemy(Player player) {
        this.player = player;
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        super.update();
        checkIfDead();
        checkIfShot();
    }

    @Override
    public void onDeath() {
        for (int i = 0; i < storedCoins; i++) {
            new DroppedCoin(x, y, player);
        }
    }

    protected void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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
