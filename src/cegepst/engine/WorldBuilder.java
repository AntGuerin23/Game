package cegepst.engine;

import cegepst.engine.controls.Direction;
import cegepst.engine.mapCollisions.Blockade;
import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.entities.enemies.Spike;
import cegepst.mainGame.entities.items.FuelContainer;
import cegepst.mainGame.entities.items.coin.Coin;
import cegepst.mainGame.entities.player.Player;
import cegepst.mainGame.miscellaneous.other.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class WorldBuilder {

    private static final int PIXEL_PER_TILE = 48;
    private Player player;

    public void buildWorldFromJSON(Resource resource, Player player) {
        JSONParser parser = new JSONParser();
        this.player = player;
        try (FileReader fileReader = new FileReader(ResourceLoader.class.getClassLoader().getResource((resource.getPath())).getPath())) {
            JSONObject globalInfo = (JSONObject) parser.parse(fileReader);
            createObjects(globalInfo);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void createObjects(JSONObject globalInfo) {
        for (int i = 0; i < 2; i++) {
            int index = 0;
            JSONArray tileData = (i == 0) ? getTileData(globalInfo) : getObjectData(globalInfo);
            for (Object tileId : tileData) {
                checkTile(tileId, index, globalInfo);
                index++;
            }
        }
    }

    private void checkTile(Object tileId, int index, JSONObject globalInfo) {
        int worldWidth = getWidth(globalInfo);
        JSONArray tileset = getTileset(globalInfo);
        checkForSpike(getObjectId(tileId), index, worldWidth);
        checkForCoin(getObjectId(tileId), index, worldWidth);
        checkForFuelContainer(getObjectId(tileId), index, worldWidth);
        if (tileIdExists((getRealTileId(tileId)), tileset)) {
            createBlockade(index, worldWidth, tileId, tileset);
        }
    }

    private void createBlockade(int index, int worldWidth, Object tileId, JSONArray tileset) {
        int locationX = index % worldWidth;
        Blockade blockade = new Blockade();
        int locationY = index / worldWidth;
        int offsetY = (int) Math.round(getTileOffsetY(getRealTileId(tileId), tileset));
        int height = (int) Math.round(getTileHeight(getRealTileId(tileId), tileset));
        blockade.teleport(locationX * PIXEL_PER_TILE,
                locationY * PIXEL_PER_TILE + offsetY);
        blockade.setDimension(PIXEL_PER_TILE,height);

    }

    private JSONArray getTileData(JSONObject globalInfo) {
        JSONArray array = ((JSONArray) globalInfo.get("layers"));
        return ((JSONArray) ((JSONObject)array.get(2)).get("data"));
    }

    private JSONArray getObjectData(JSONObject globalInfo) {
        JSONArray array = ((JSONArray) globalInfo.get("layers"));
        return ((JSONArray) ((JSONObject)array.get(1)).get("data"));
    }

    private int getWidth(JSONObject globalInfo) {
        return Integer.parseInt(globalInfo.get("width").toString());
    }

    private JSONArray getTileset(JSONObject globalInfo) {
        return (JSONArray) ((JSONObject)((JSONArray) globalInfo.get("tilesets")).get(0)).get("tiles");
    }

    private boolean tileIdExists(String tileId, JSONArray tiles) {
        for (Object tile : tiles) {
            if (((JSONObject) tile).get("id").toString().equals(tileId)) {
                return true;
            }
        }
        return false;
    }

    private double getTileOffsetY(String tileId, JSONArray tileset) {
        return Double.parseDouble(findTileValues(tileId,tileset).get("y").toString());
    }

    private double getTileHeight(String tileId, JSONArray tileset) {
        return Double.parseDouble(findTileValues(tileId,tileset).get("height").toString());
    }

    private JSONObject findTileValues(String tileId, JSONArray tileset) {
        for (Object tile : tileset) {
            if (((JSONObject) tile).get("id").toString().equals(tileId)) {
                return (JSONObject)((JSONArray)((JSONObject)((JSONObject) tile).get("objectgroup")).get("objects")).get(0);
            }
        }
        return null;
    }

    private String getRealTileId(Object tileId) {
        return String.valueOf(Integer.parseInt(tileId.toString()) - 1);
    }

    private int getObjectId(Object tileId) {
        return Integer.parseInt(tileId.toString());
    }

    private void checkForSpike(int tileId, int index, int worldWidth) {
        if (getObjectId(tileId) >= 22 && getObjectId(tileId) <= 25) {
            createSpike(tileId, index, worldWidth);
        }
    }

    private void createSpike(int tileId, int index, int worldWidth) {
        int locationX = index % worldWidth;
        int locationY = index / worldWidth;
        new Spike(locationX * PIXEL_PER_TILE,locationY * PIXEL_PER_TILE, getSpikeDirection(tileId));
    }

    private Direction getSpikeDirection(int tileId) {
        if (tileId == 22) {
            return Direction.UP;
        }
        if (tileId == 23) {
            return Direction.RIGHT;
        }
        if (tileId == 24) {
            return Direction.DOWN;
        }
        if (tileId == 25) {
            return Direction.LEFT;
        }
        return null;
    }

    private void checkForCoin(int tileId, int index, int worldWidth) {
        if (getObjectId(tileId) == 21) {
            int locationX = index % worldWidth;
            int locationY = index / worldWidth;
            new Coin(locationX * PIXEL_PER_TILE, locationY * PIXEL_PER_TILE, player);
        }
    }

    private void checkForFuelContainer(int tileId, int index, int worldWidth) {
        if (getObjectId(tileId) == 34) {
            int locationX = index % worldWidth;
            int locationY = index / worldWidth;
            new FuelContainer(locationX * PIXEL_PER_TILE, locationY * PIXEL_PER_TILE);
        }
    }
}
