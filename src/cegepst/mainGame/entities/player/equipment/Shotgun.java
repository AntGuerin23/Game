package cegepst.mainGame.entities.player.equipment;

import cegepst.engine.graphics.Buffer;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.GamePad;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public class Shotgun implements Equipable {

    private Player player;
    private GamePad controller;
    private Image sprite;

    public void initialize(Player player, GamePad controller) {
        this.player = player;
        this.controller = controller;
        sprite = ResourceLoader.loadSprite(Resource.SHOTGUN_SPRITE.getPath());
    }

    @Override
    public void updateEquipment() {
        if (controller.isFirePressed()) {
            EntityRepository.getInstance().registerEntityBuffered(new Bullet(player));
        }
    }

    public void draw(Buffer buffer) {
        buffer.drawFlippableImage(sprite, player.getX() + 5, player.getX() - 17, player.getY() + 20,54, 22,player.getHorizontalDirection());
    }

    @Override
    public String toString() {
        return "Shotgun";
    }
}
