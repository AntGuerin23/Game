package cegepst.engine;

import cegepst.engine.entities.StaticEntity;
import cegepst.mainGame.entities.Blockade;
import cegepst.mainGame.entities.Bullet;

public class IntersectionChecker {



    public static StaticEntity checkIntersect(StaticEntity entity, String searchedEntity) {
        for (StaticEntity staticEntity : EntityRepository.getInstance()) {
            if (entity.intersectsWith(staticEntity)
                    && staticEntity.getClass().toString().equalsIgnoreCase(searchedEntity)) {
                return staticEntity;
            }
        }

        return null;
    }
}
