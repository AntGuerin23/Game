package cegepst.mainGame.entities.player.equipment;

import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;

public interface Equipable {

    void initialize(Player player, GamePad controller);
    void updateEquipment();

}
