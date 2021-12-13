package cegepst.mainGame.entities.environment;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.IntersectionChecker;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Crate extends MovableEntity {

    private Player player;
    private Image sprite;

    public Crate(int x, int y, Player player) {
        setGravitating(true);
        this.player = player;
        sprite = ResourceLoader.loadSprite(Resource.CRATE.getPath());
        EntityRepository.getInstance().registerEntity(this,true);
        setDimension(48,48);
        setSpeed(2);
        teleport(x,y);
    }

    @Override
    public void update() {
        super.update();
        if (player.isPressingLeftAndRightButtons() && player.isTouchingWall() && player.hitBoxIntersectsWith(this) && player.isGrounded()) {
            horizontalDirection = player.getHorizontalDirection();
            moveHorizontally(horizontalDirection);
        }
        StaticEntity entity = IntersectionChecker.checkHitboxIntersect(this,"Crate");
        if (entity != null && player.isPressingLeftAndRightButtons() && player.isTouchingWall() && player.hitBoxIntersectsWith(entity)) {
            horizontalDirection = ((Crate) entity).horizontalDirection;
            moveHorizontally(horizontalDirection);
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }

    @Override
    public String toString() {
        return "Crate";
    }
}
