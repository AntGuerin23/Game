package cegepst.mainGame.entities.environment;

import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.GameTime;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.player.equipment.Bullet;
import cegepst.mainGame.miscellaneous.other.Randomizer;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class CrackedBlock extends MovableEntity {

    private static final int MAX_HP = 3;
    private static final int STUN_TIME = 7;
    private Image sprite;
    private int hp;
    private int moveIndex;
    private boolean stunned;
    private Bullet lastBullet;

    public CrackedBlock(int x, int y) {
        teleport(x,y);
        setDimension(48,48);
        sprite = ResourceLoader.loadSprite(Resource.CRACKED_BLOCK.getPath());
        moveIndex = 0;
        setGravitating(true);
        setSpeed(2);
        hp = MAX_HP;
        EntityRepository.getInstance().registerEntity(this, true);
    }

    @Override
    public void update() {
        super.update();
        firstAirFrameTime = GameTime.getCurrentTime() + 200;
        checkForBullet();
        if (stunned) {
            moveAround();
            updateStun();
        }
        checkIfDead();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite,x,y);
    }

    private void checkForBullet() {
        if (!stunned) {
            Bullet bullet = (Bullet) IntersectionChecker.checkHitboxIntersect(this,"Bullet");
            if (bullet != null) {
                if (hp == 1) {
                    lastBullet = bullet;
                }
                bullet.kill();
                getHit();
                for (int i = 0; i < 4; i++) {
                    new BlockFragment(x + ((bullet.getHorizontalDirection() == Direction.RIGHT) ? -3 : 51),y, bullet.getHorizontalDirection());
                }
            }
        }
    }

    private void moveAround() {
        Direction direction = Direction.values()[moveIndex];
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            moveHorizontally(direction);
        }
        moveIndex++;
        if (moveIndex >= 4) {
            moveIndex = 0;
        }
    }

    private void updateStun() {
        stunStatus--;
        if (stunStatus <= 0) {
            stunned = false;
        }
    }

    private void checkIfDead() {
        if (hp <= 0) {
            isDead = true;
        }
    }

    private void getHit() {
        stunned = true;
        stunStatus = STUN_TIME;
        hp--;
    }

    @Override
    public void onDeath() {
        super.onDeath();
        for (int i = 0; i < 30; i++) {
            new BlockFragment(x + 20, y, (Randomizer.randomInt(0, 1) == 0) ? Direction.LEFT : Direction.RIGHT);
        }
    }
}
