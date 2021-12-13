package cegepst.mainGame.entities.player.equipment;

import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;

public class ClimbingGloves implements Equipable {

    private Player player;

    public void initialize(Player player, GamePad controller) {
        this.player = player;
    }

    @Override
    public void updateEquipment() {
        player.climb();
    }

    @Override
    public String toString() {
        return "ClimbingGloves";
    }
}
