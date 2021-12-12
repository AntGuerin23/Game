package cegepst.mainGame.entities.objects;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.graphics.Buffer;
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
        setSpeed(1);
        teleport(x,y);
    }

    @Override
    public void update() {
        super.update();
        if (player.isPressingLeftAndRightButtons() && player.isTouchingWall() && player.hitBoxIntersectsWith(this)) {
            moveHorizontally(player.getHorizontalDirection());
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite, x, y);
    }
}
