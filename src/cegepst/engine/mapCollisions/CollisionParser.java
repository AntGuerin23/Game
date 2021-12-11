package cegepst.engine.mapCollisions;

import cegepst.engine.resources.ResourceLoader;
import cegepst.mainGame.miscellaneous.other.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CollisionParser {

    private static final int PIXEL_PER_TILE = 48;

    public ArrayList<Blockade> createBlockades(Resource resource) {
        ArrayList<Blockade> blockades = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(ResourceLoader.class.getClassLoader().getResource((resource.getPath())).getPath())) {
            JSONObject globalInfo = (JSONObject) parser.parse(fileReader);
            blockades = createBlockades(globalInfo);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return blockades;
    }

    private ArrayList<Blockade> createBlockades(JSONObject globalInfo) {
        ArrayList<Blockade> blockades = new ArrayList<>();
        int index = 0;
        JSONArray tileData = getTileData(globalInfo);
        for (Object tileId : tileData) {
            Blockade blockade = createBlockade(tileId, index, globalInfo);
            blockades.add(blockade);
            index++;
        }
        return blockades;
    }

    private Blockade createBlockade(Object tileId, int index, JSONObject globalInfo) {
        Blockade blockade = new Blockade();
        int worldWidth = getWidth(globalInfo);
        JSONArray tileset = getTileset(globalInfo);
        if (tileIdExists((getRealTileId(tileId)), tileset)) {
            int locationX = index % worldWidth;
            int locationY = index / worldWidth;
            int offsetY = (int) Math.round(getTileOffsetY(getRealTileId(tileId), tileset));
            int height = (int) Math.round(getTileHeight(getRealTileId(tileId), tileset));
            blockade.teleport(locationX * PIXEL_PER_TILE,
                    locationY * PIXEL_PER_TILE + offsetY);
            blockade.setDimension(PIXEL_PER_TILE,height);
        }
        return blockade;
    }

    private JSONArray getTileData(JSONObject globalInfo) {
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
}
