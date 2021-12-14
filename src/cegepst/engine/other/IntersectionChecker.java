package cegepst.engine.other;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.repositories.EntityRepository;
import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.worlds.World;

import java.util.Map;

public class IntersectionChecker {

    public static StaticEntity checkIntersect(StaticEntity entity, String searchedEntity) {
        for (Map.Entry<StaticEntity, World> entry : EntityRepository.getInstance().getRepository()) {
            StaticEntity staticEntity = entry.getKey();
            if (entity.intersectsWith(staticEntity) && MainGame.getInstance().isPlayerNear(staticEntity)
                    && staticEntity.toString().equalsIgnoreCase(searchedEntity)
                    && entry.getValue() == MainGame.getInstance().getCurrentWorld()) {
                return staticEntity;
            }
        }

        return null;
    }

    public static StaticEntity checkHitboxIntersect(StaticEntity entity, String searchedEntity) {
        for (Map.Entry<StaticEntity, World> entry : EntityRepository.getInstance().getRepository()) {
            StaticEntity staticEntity = entry.getKey();
            if (staticEntity instanceof MovableEntity && MainGame.getInstance().isPlayerNear(staticEntity)) {
                if (((MovableEntity) staticEntity).hitBoxIntersectsWith(entity)
                        && staticEntity.toString().equalsIgnoreCase(searchedEntity)
                        && entry.getValue() == MainGame.getInstance().getCurrentWorld()) {
                    return staticEntity;
                }
            }
        }
        return null;
    }
}
