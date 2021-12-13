package cegepst.mainGame.entities.environment;

import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.player.equipment.ClimbingGloves;
import cegepst.mainGame.entities.player.equipment.Equipable;
import cegepst.mainGame.entities.player.equipment.Jetpack;
import cegepst.mainGame.entities.player.equipment.Shotgun;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public enum ShopItem {

    SHOTGUN(Resource.SHOTGUN_SPRITE.getPath(), Resource.SHOOT_TEXT.getPath(), new Shotgun() , 75, 540),
    JETPACK(Resource.JETPACK_SPRITE.getPath(), Resource.FLY_TEXT.getPath(), new Jetpack(), 100, 570),
    GLOVES(Resource.GLOVE_SPRITE.getPath(), Resource.CLIMB_TEXT.getPath(), new ClimbingGloves(), 125, 600);

    private final Image image;
    private final Image tutorial;
    private final Equipable item;
    private final int price;
    private final int tutorialLocationY;

    ShopItem(String imagePath, String tutorialPath, Equipable item, int price, int tutorialLocationY) {
        image = ResourceLoader.loadSprite(imagePath);
        tutorial = ResourceLoader.loadSprite(tutorialPath);
        this.item = item;
        this.price = price;
        this.tutorialLocationY = tutorialLocationY;
    }

    public Image getImage() {
        return image;
    }

    public Image getTutorialText() {
        return tutorial;
    }

    public Equipable getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getTutorialLocationY() {
        return tutorialLocationY;
    }
}
