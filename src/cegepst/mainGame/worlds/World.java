package cegepst.mainGame.worlds;

import cegepst.engine.other.Camera;
import cegepst.engine.resources.ResourceLoader;
import cegepst.engine.graphics.Buffer;
import cegepst.engine.mapCollisions.Blockade;
import cegepst.mainGame.miscellaneous.other.Resource;

import java.awt.*;

public abstract class World {

    private static final int BORDER_Y_HEIGHT = 30;
    private static final int BORDER_X_WIDTH = 30;
    private Image background;
    protected int startBorderX;
    protected int startBorderY;
    protected int endBorderX;
    protected int endBorderY;

    protected abstract void drawEntities(Buffer buffer);
    public abstract int getSpawnPointX();
    public abstract int getSpawnPointY();

    public void draw(Buffer buffer, Camera camera) {
        buffer.translate(camera);
        if (background != null) {
            buffer.drawImage(background,0,0);
        }
        drawEntities(buffer);
    }

    public int getStartBorderX() {
        return startBorderX;
    }

    public int getStartBorderY() {
        return startBorderY;
    }

    public int getEndBorderX() {
        return endBorderX;
    }

    public int getEndBorderY() {
        return endBorderY;
    }

    protected void setBackground(Resource resource) {
        background = ResourceLoader.loadSprite(resource.getPath());
    }

    protected void createBorders() {
        createWall(startBorderX, true);
        createWall(endBorderX, false);
        createFloor(startBorderY, true);
        createFloor(endBorderY, false);
    }

    private void createFloor(int floorLocation, boolean isStartBorder) {
        Blockade floor = new Blockade();
        floor.teleport(0,floorLocation - ((isStartBorder) ? BORDER_Y_HEIGHT : 0));
        floor.setDimension(10000,BORDER_Y_HEIGHT);
    }

    private void createWall(int wallLocation, boolean isStartBorder) {
        Blockade wall = new Blockade();
        wall.teleport(wallLocation - ((isStartBorder) ? BORDER_X_WIDTH : 0),0);
        wall.setDimension(BORDER_X_WIDTH,10000);
    }

}
