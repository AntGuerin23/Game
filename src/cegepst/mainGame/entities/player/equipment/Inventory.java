package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.Camera;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;

import java.util.HashMap;

public class Inventory {

    private final HashMap<String, Equipable> inventory;
    private final GamePad controller;
    private final Player player;
    private Shotgun shotgun;
    private Jetpack jetpack;

    public Inventory(Player player, GamePad controller) {
        inventory = new HashMap<>();
        this.controller = controller;
        this.player = player;
    }

    public void update() {
        updateVariables();
        for (Equipable equipable : inventory.values()) {
            equipable.updateEquipment();
        }
    }

    public void drawShotgun(Buffer buffer) {
        if (shotgun != null) {
            shotgun.draw(buffer);
        }
    }

    public void drawJetpack(Buffer buffer) {
        if (jetpack != null) {
            jetpack.asyncDraw(buffer);
        }
    }

    public void drawFuelGage(Buffer buffer, Camera camera) {
        jetpack.drawFuelGage(buffer, camera);
    }

    public boolean isJetpackFlying() {
        if (jetpack != null) {
            return jetpack.isFlying();
        }
        return false;
    }

    public boolean hasJetpack() {
        return jetpack != null;
    }

    public void addItem(Equipable item) {
        item.initialize(player, controller);
        inventory.put(item.toString(), item);
    }


    public void restartJetpackSound() {
        jetpack.setIsPlaySoundReady(true);
    }

    private void updateVariables() {
        jetpack = ((Jetpack) inventory.get("Jetpack"));
        shotgun = ((Shotgun) inventory.get("Shotgun"));
    }
}
