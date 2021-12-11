package cegepst.mainGame.entities.items;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.resources.*;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.actions.CoinActions;
import cegepst.mainGame.miscellaneous.other.Resource;

public class Coin extends MovableEntity implements Animatable {

    private Player player;
    private Animator animator;
    protected int value;

    public Coin(int x, int y, Player player) {
        this.player = player;
        teleport(x,y);
        setDimension(32,31);
        setGravitating(false);
        EntityRepository.getInstance().registerEntity(this,false);
        animator = new Animator(this, Resource.COIN_SPRITE_SHEET, 1, CoinActions.IDLE, 32);
        value = 1;
    }

    @Override
    public void update() {
        super.update();
        checkIfPickedUp();
        animator.animate();
    }

    @Override
    public void draw(Buffer buffer) {
        animator.drawAnimation(buffer, x, y);
    }

    @Override
    public void onDeath() {
        new CoinEffect(x, y);
        Sound.playOnce(ResourceLoader.loadSound(Resource.COIN_SOUND_EFFECT.getPath()), -10);
    }

    private void checkIfPickedUp() {
        StaticEntity intersectingEntity = IntersectionChecker.checkIntersect(this, "Player");
        if (intersectingEntity != null) {
            player.foundCoin(value);
            super.isDead = true;
        }
    }

    @Override
    public boolean restartAnimation(Action currentAction) {
        return false;
    }

    @Override
    public void onAnimationEnd(Action action) {

    }
}
