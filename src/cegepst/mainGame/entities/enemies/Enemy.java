package cegepst.mainGame.entities.enemies;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.repositories.EntityRepository;
import cegepst.mainGame.entities.items.coin.DroppedCoin;
import cegepst.mainGame.entities.player.Player;

public abstract class Enemy extends MovableEntity {

    private static final int STUN_DURATION = 20;
    protected int hp;
    protected int maxHp;
    protected int storedCoins;
    private Player player;
    protected boolean stunned;

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
            getHit();
            stunned = true;
            stunStatus = STUN_DURATION;
            intersectingEntity.kill();
        }
    }

    private void getHit() {
        hp--;
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
