package cegepst.engine.graphics;

import cegepst.engine.Camera;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;

import java.awt.*;

public class Buffer {

    private final Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void translate(Camera camera) {
        graphics.translate(-camera.getX(), -camera.getY());
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawOval(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, width, height);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawImage(Image image, MovableEntity entity) {
        if (entity.getHorizontalDirection() == Direction.RIGHT) {
            graphics.drawImage(image, entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), null);
            return;
        }
        graphics.drawImage(image, entity.getX() + entity.getWidth(), entity.getY(), -entity.getWidth(), entity.getHeight(), null);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

}
