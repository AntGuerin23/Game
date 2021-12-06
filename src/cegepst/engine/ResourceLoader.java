package cegepst.engine;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class ResourceLoader {

    public static Image loadSprite(String path) {
        try {
            return ImageIO.read(loadResourceStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage loadSpriteSheet(String path) {
        return (BufferedImage) loadSprite(path);
    }

    public static String loadFilePath(String path) {
        return loadResource(path).getPath();
    }

    private static URL loadResource(String path) {
        return Objects.requireNonNull(getResources().getResource(path));
    }

    private static InputStream loadResourceStream(String path) {
        return Objects.requireNonNull(getResources().getResourceAsStream(path));
    }

    private static ClassLoader getResources() {
        return ResourceLoader.class.getClassLoader();
    }
}
