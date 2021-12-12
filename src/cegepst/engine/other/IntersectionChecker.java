package cegepst.engine.other;

import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.worlds.World;

import java.util.Map;

public class IntersectionChecker {

    public static StaticEntity checkIntersect(StaticEntity entity, String searchedEntity) {
        for (Map.Entry<StaticEntity, World> entry : EntityRepository.getInstance().getRepository()) {
            StaticEntity staticEntity = entry.getKey();
            if (entity.intersectsWith(staticEntity)
                    && staticEntity.toString().equalsIgnoreCase(searchedEntity)
                    && entry.getValue() == MainGame.getInstance().getCurrentWorld()) {
                return staticEntity;
            }
        }

        return null;
    }
}
