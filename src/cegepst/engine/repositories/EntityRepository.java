package cegepst.engine.repositories;

import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.worlds.World;

import java.util.*;

public class EntityRepository {

    private static EntityRepository instance;
    private final HashMap<StaticEntity, World> registeredEntities;
    private final HashMap<StaticEntity, World> creationBuffer;

    public static EntityRepository getInstance() {
        if (instance == null) {
            instance = new EntityRepository();
        }
        return instance;
    }

    public Set<Map.Entry<StaticEntity, World>> getRepository() {
        return registeredEntities.entrySet();
    }

    public void registerEntities(Collection<StaticEntity> entities, boolean isCollidable) {
        for (StaticEntity entity : entities) {
            registeredEntities.put(entity, MainGame.getInstance().getCurrentWorld());
            if (isCollidable) {
                CollidableRepository.getInstance().registerEntity(entity);
            }
        }
    }

    public void registerEntityBuffered(StaticEntity staticEntity) {
        creationBuffer.put(staticEntity, MainGame.getInstance().getCurrentWorld());
    }

    public void emptyCreationBuffer() {
        registerEntities(creationBuffer.keySet(), false);
        creationBuffer.clear();
    }

    public void registerEntity(StaticEntity entity, boolean isCollidable) {
        registeredEntities.put(entity, MainGame.getInstance().getCurrentWorld());
        if (isCollidable) {
            CollidableRepository.getInstance().registerEntity(entity);
        }
    }

    public void unregisterEntity(StaticEntity entity) {
        registeredEntities.remove(entity);
        CollidableRepository.getInstance().unregisterEntity(entity);
    }

    public void unregisterEntities(Collection<StaticEntity> entities) {
        for (StaticEntity entity : entities) {
            registeredEntities.remove(entity);
            CollidableRepository.getInstance().unregisterEntity(entity);
        }
    }

    private EntityRepository() {
        creationBuffer = new HashMap<>();
        registeredEntities = new HashMap<>();
    }
}
