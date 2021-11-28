package cegepst.engine;


import cegepst.mainGame.miscellaneous.other.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceLoader {

    public static Image loadSprite(Resource resource) {
        try {
            return ImageIO.read(Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResourceAsStream(resource.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage loadSpriteSheet(Resource resource) {
        return (BufferedImage) loadSprite(resource);
    }
}
