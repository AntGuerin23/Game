package cegepst.mainGame.entities.enemies;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.resources.Action;
import cegepst.engine.resources.Animatable;
import cegepst.engine.resources.Animator;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.actions.RatActions;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Rat extends Bouncer implements Animatable {

    private Animator animator;
    private Action lastAction;

    public Rat(int x, int y, int roamDistance, Player player, boolean canBounce, int nbOfCoins) {
        super(x, y, roamDistance, player, canBounce, nbOfCoins);
        initializeValues();
    }

    @Override
    public void update() {
        super.update();
        animator.animate();
        updateStun();
    }

    @Override
    public void draw(Buffer buffer) {
        animator.drawFlippableAnimation(buffer,x,x,y);
        buffer.drawRectangle(x - 2, y - 22, width + 4, 14, Color.DARK_GRAY);
        buffer.drawRectangle(x, y - 20, width, 10, Color.RED);
        buffer.drawRectangle(x, y - 20, (int) ((double) width / maxHp * super.hp), 10, Color.GREEN);
    }

    @Override
    public Action getNextAction(Action currentAction) {
        if (stunStatus > 0) {
            return RatActions.STUNNED;
        }
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

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    private void initializeValues() {
        setMaxHp(10);
        setDimension(74,76);
        setSpeed(canBounce ? 4 : 6);
        animator = new Animator(this, Resource.RAT_SPRITE_SHEET, 3, RatActions.RUN,width);
    }

    private void updateStun() {
        if (stunStatus <= 0) {
            stunned = false;
        }
        stunStatus--;
    }
}
