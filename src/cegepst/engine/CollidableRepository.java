package cegepst.engine;

import cegepst.engine.entities.StaticEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollidableRepository implements Iterable<StaticEntity> {

    private static CollidableRepository instance;
    private final List<StaticEntity> registeredEntities;

    public static CollidableRepository getInstance() {
        if (instance == null) {
            instance = new CollidableRepository();
        }
        return instance;
    }

    protected void registerEntities(Collection<StaticEntity> entities) {
        registeredEntities.addAll(entities);
    }

    protected void registerEntity(StaticEntity entity) {
        registeredEntities.add(entity);
    }

    protected void unregisterEntity(StaticEntity entity) {
        registeredEntities.remove(entity);
    }

    public void unregisterEntities(Collection<StaticEntity> entities) {
        registeredEntities.removeAll(entities);
    }

    public int count() {
        return registeredEntities.size();
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return registeredEntities.iterator();
    }

    private CollidableRepository() {
        registeredEntities = new ArrayList<>();
    }
}
