package cegepst.mainGame.entities.items;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class CoinBag extends Coin {

    private Image sprite;

    public CoinBag(int x, int y, Player player, int value) {
        super(x, y, player);
        this.value = value;
        sprite = ResourceLoader.loadSprite(Resource.COIN_BAG_SPRITE.getPath());
        setDimension(32,32);
        setGravitating(true);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(sprite,x,y);
    }
}
