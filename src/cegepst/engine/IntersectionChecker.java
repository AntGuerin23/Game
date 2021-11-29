package cegepst.engine;

import cegepst.engine.entities.StaticEntity;

public class IntersectionChecker {

    public static StaticEntity checkIntersect(StaticEntity entity, String searchedEntity) {
        for (StaticEntity staticEntity : EntityRepository.getInstance()) {
            if (entity.intersectsWith(staticEntity)
                    && staticEntity.toString().equalsIgnoreCase(searchedEntity)) {
                return staticEntity;
            }
        }

        return null;
    }
}
