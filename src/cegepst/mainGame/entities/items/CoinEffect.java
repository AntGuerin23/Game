package cegepst.mainGame.entities.items;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.Action;
import cegepst.engine.resources.Animatable;
import cegepst.engine.resources.Animator;
import cegepst.mainGame.miscellaneous.actions.CoinEffectActions;
import cegepst.mainGame.miscellaneous.other.Resource;

public class CoinEffect extends MovableEntity implements Animatable {

    private Animator animator;

    public CoinEffect(int x, int y) {
        setDimension(34,15);
        teleport(x,y + 7);
        animator = new Animator(this, Resource.COIN_EFFECT_SPRITE_SHEET,1, CoinEffectActions.DISAPPEAR, 34);
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        animator.animate();
    }

    @Override
    public void draw(Buffer buffer) {
        animator.drawAnimation(buffer, x, y);
    }

    @Override
    public boolean restartAnimation(Action currentAction) {
        return false;
    }

    @Override
    public void onAnimationEnd(Action action) {
        isDead = true;
    }
}
