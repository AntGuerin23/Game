package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.other.Camera;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;

import javax.swing.*;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Equipable> inventory;
    private GamePad controller;
    private Player player;
    private Shotgun shotgun;
    private Jetpack jetpack;

    public Inventory(Player player, GamePad controller) {
        inventory = new ArrayList<>();
        this.controller = controller;
        this.player = player;
    }

    public void update() {
        for (Equipable equipable : inventory) {
            equipable.updateEquipment();
        }
    }

    public void drawShotgun(Buffer buffer) {
        if (inventory.contains(shotgun)) {
            shotgun.draw(buffer);
        }
    }

    public void drawJetpack(Buffer buffer) {
        if (inventory.contains(jetpack)) {
            jetpack.asyncDraw(buffer);
        }
    }

    public void drawFuelGage(Buffer buffer, Camera camera) {
        jetpack.drawFuelGage(buffer, camera);
    }

    public boolean isJetpackFlying() {
        if (inventory.contains(jetpack)) {
            return jetpack.isFlying();
        }
        return false;
    }

    public boolean hasJetpack() {
        return inventory.contains(jetpack);
    }

    public void pickupShotgun() {
        shotgun = new Shotgun(player, controller);
        inventory.add(shotgun);
    }

    public void pickupJetpack() {
        jetpack = new Jetpack(player, controller);
        inventory.add(jetpack);
    }

    public void pickupClimbingGloves() {
        ClimbingGloves gloves = new ClimbingGloves(player);
        inventory.add(gloves);
    }
}
