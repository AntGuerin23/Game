package cegepst.mainGame.entities.enemies;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.Action;
import cegepst.engine.resources.Animatable;
import cegepst.engine.resources.Animator;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.actions.RatActions;
import cegepst.mainGame.miscellaneous.other.Resource;

public class Rat extends Bouncer implements Animatable {

    private Animator animator;
    private Action lastAction;

    public Rat(int x, int y, int roamDistance, Player player, boolean canBounce) {
        super(x, y, roamDistance, player, canBounce);
        initializeValues();
        EntityRepository.getInstance().registerEntity(this,false);
    }

    @Override
    public void update() {
        super.update();
        animator.animate();
    }

    @Override
    public void draw(Buffer buffer) {
        animator.drawFlippableAnimation(buffer,x,x,y);
    }

    @Override
    public Action getNextAction(Action currentAction) {
        //TODO : Stunned
        if (!isGrounded()) {
            return RatActions.JUMP;
        }
        return RatActions.RUN;
    }

    @Override
    public boolean restartAnimation(Action currentAction) {
        boolean restart = false;
        if (!hasMoved() || lastAction != currentAction) {
            restart = true;
        }
        lastAction = currentAction;
        return restart;
    }

    @Override
    public void onAnimationEnd(Action action) {}

    private void initializeValues() {
        setMaxHp(3);
        setDimension(74,76);
        setSpeed(canBounce ? 2 : 3);
        storedCoins = canBounce ? 10 : 5;
        animator = new Animator(this, Resource.RAT_SPRITE_SHEET, 3, RatActions.RUN,width);
    }
}
