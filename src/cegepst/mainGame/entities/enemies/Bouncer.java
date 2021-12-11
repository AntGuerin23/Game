package cegepst.mainGame.entities.enemies;

import cegepst.mainGame.entities.player.Player;

public abstract class Bouncer extends Roamer {

    private int jumpCooldown = 50;
    protected boolean canBounce;

    public Bouncer(int x, int y, int roamDistance, Player player, boolean canBounce) {
        super(x, y, roamDistance, player);
        initializeValues(canBounce);
        setGravitating(true);
        setJumpForce(10);
    }

    @Override
    public void update() {
        super.update();
        if (canBounce) {
            if (jumpCooldown == 0) {
                jump();
                jumpCooldown = 120;
            }
            jumpCooldown--;
        }
    }

    private void initializeValues(boolean canBounce) {
        this.canBounce = canBounce;
    }
}
