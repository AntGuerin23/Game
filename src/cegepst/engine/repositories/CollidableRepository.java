package cegepst.engine.repositories;

import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.worlds.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CollidableRepository {

    private static CollidableRepository instance;
    private final HashMap<StaticEntity, World> registeredEntities;

    public static CollidableRepository getInstance() {
        if (instance == null) {
            instance = new CollidableRepository();
        }
        return instance;
    }

    public Set<Map.Entry<StaticEntity, World>> getRepository() {
        return registeredEntities.entrySet();
    }

    public void registerEntity(StaticEntity entity) {
        registeredEntities.put(entity, MainGame.getInstance().getCurrentWorld());
    }

    protected void unregisterEntity(StaticEntity entity) {
        registeredEntities.remove(entity);
    }

    private CollidableRepository() {
        registeredEntities = new HashMap<>();
    }
}
