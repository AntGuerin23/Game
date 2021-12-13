package cegepst.mainGame.entities.environment;

import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.entities.player.equipment.Equipable;

public class BuyStation extends StaticEntity {

    private static final int TEXT_LOCATION_X = 800;
    private ShopItem item;
    private boolean hasBeenBought;

    public BuyStation(int x, int y, ShopItem item) {
        setDimension(80,200);
        teleport(x - 10, y);
        this.item = item;
        hasBeenBought = false;
        EntityRepository.getInstance().registerEntity(this,false);
    }

    public Equipable buyItem(Player player) {
        if (!hasBeenBought && player.getCoinCount() >= item.getPrice()) {
            player.modifyCoinCount(-item.getPrice());
            hasBeenBought = true;
            return item.getItem();
        }
        return null;
    }

    @Override
    public void draw(Buffer buffer) {
        if (!hasBeenBought) {
            buffer.drawImage(item.getImage(), x + 10 ,y);
        } else {
            buffer.drawImage(item.getTutorialText(),TEXT_LOCATION_X,item.getTutorialLocationY());
        }
    }

    @Override
    public String toString() {
        return "BuyStation";
    }
}
