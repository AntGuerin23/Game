package cegepst.engine.graphics;

import cegepst.engine.other.Camera;
import cegepst.engine.controls.Direction;

import java.awt.*;
import java.awt.font.TextLayout;

public class Buffer {

    private final Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
        graphics.setFont(new Font("SansSerif", Font.PLAIN, 22));
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
        TextLayout textLayout = new TextLayout(text, (new Font("SansSerif", Font.PLAIN, 22)), graphics.getFontRenderContext());
        graphics.setPaint(Color.BLACK);
        textLayout.draw(graphics, x, y);
        graphics.setPaint(new Color(150, 150, 150));
        textLayout.draw(graphics, x + 1, y + 1);
    }

    public void drawFlippableImage(Image image, int x, int flippedX, int y, int width, int height, Direction direction) {
        if (direction == Direction.RIGHT) {
            graphics.drawImage(image, x, y, width, height, null);
            return;
        }
        graphics.drawImage(image, flippedX + width, y, -width, height, null);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }
}
