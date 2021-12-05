package cegepst.engine;

import cegepst.engine.entities.StaticEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EntityRepository implements Iterable<StaticEntity> {

    private static EntityRepository instance;
    private final List<StaticEntity> registeredEntities;

    public static EntityRepository getInstance() {
        if (instance == null) {
            instance = new EntityRepository();
        }
        return instance;
    }

    public void registerEntities(Collection<StaticEntity> entities, boolean isCollidable) {
        registeredEntities.addAll(entities);
        if (isCollidable) {
            CollidableRepository.getInstance().registerEntities(entities);
        }
    }

    public void registerEntity(StaticEntity entity, boolean isCollidable) {
        registeredEntities.add(entity);
        if (isCollidable) {
            CollidableRepository.getInstance().registerEntity(entity);
        }
    }

    public void unregisterEntity(StaticEntity entity) {
        registeredEntities.remove(entity);
        CollidableRepository.getInstance().unregisterEntity(entity);
    }

    public void unregisterEntities(Collection<StaticEntity> entities) {
        registeredEntities.removeAll(entities);
        CollidableRepository.getInstance().unregisterEntities(entities);
    }

    public int count() {
        return registeredEntities.size();
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return registeredEntities.iterator();
    }

    private EntityRepository() {
        registeredEntities = new ArrayList<>();
    }
}
