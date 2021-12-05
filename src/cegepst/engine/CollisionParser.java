package cegepst.engine;

import cegepst.mainGame.entities.Blockade;
import cegepst.mainGame.miscellaneous.other.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CollisionParser {

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
        if (tileIdExists(tileId.toString(), getTiles(globalInfo))) {
            int locationX = index % worldWidth;
            int locationY = index / worldWidth;
            //System.out.println("id : " + tileId + ("X : " + locationX + ",  Y : " + locationY));
            blockade.teleport(locationX * 48, locationY * 48);
            blockade.setDimension(48,48); //TODO : Make this responsive
        }
        return blockade;
    }

    private JSONArray getTileData(JSONObject globalInfo) {
        return ((JSONArray) ((JSONObject)((JSONArray) globalInfo.get("layers")).get(0)).get("data"));
    }

    private int getWidth(JSONObject globalInfo) {
        return Integer.parseInt(globalInfo.get("width").toString());
    }

    private JSONArray getTiles(JSONObject globalInfo) {
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
}
