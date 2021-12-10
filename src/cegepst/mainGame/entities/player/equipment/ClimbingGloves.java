package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.other.GameTime;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;

public class ClimbingGloves implements Equipable {

    private Player player;

    public ClimbingGloves(Player player) {
        this.player = player;
    }

    @Override
    public void updateEquipment() {
        player.climb();
    }
}
