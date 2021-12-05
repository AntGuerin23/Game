package cegepst.mainGame.worlds;

import cegepst.engine.EntityRepository;
import cegepst.engine.ResourceLoader;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.graphics.Buffer;
import cegepst.mainGame.entities.Player;
import cegepst.mainGame.miscellaneous.other.Resource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;


public class TestWorld extends World {

    private Player player;

    public TestWorld(Player player) {
        this.player = player;
        initializeBorderLocations();
        instantiateBorders();
        initializeBlockades();
        setBackground(Resource.TEST_WORLD_IMG_PATH);
    }

    @Override
    protected void drawEntities(Buffer buffer) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            entity.draw(buffer);
        }
    }

    private void initializeBorderLocations() {
        super.startBorderX = 0;
        super.startBorderY = 0;
        super.endBorderX = 1440;
        super.endBorderY = 1440;
    }

    private void instantiateBorders() {
        super.createBorders();
    }

    private void initializeBlockades() {
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(ResourceLoader.class.getClassLoader().getResource((Resource.TEST_WORLD_JSON_PATH.getPath())).getPath())) {
            JSONObject collisionInfo = (JSONObject) parser.parse(fileReader);


            System.out.println(collisionInfo.get("tileheight"));

//                Room room = new Room();
//                JSONObject roomInfo = (JSONObject) collisionJSONArray.get(i);
//                room.initializeRoom(roomInfo);
//                rooms.add(room);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
