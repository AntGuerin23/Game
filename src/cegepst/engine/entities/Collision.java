package cegepst.engine.entities;

import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.Direction;

import java.awt.*;

public class Collision {

    private final MovableEntity entity;

    public Collision(MovableEntity entity) {
        this.entity = entity;
    }

    public double getAllowedSpeed(Direction direction) {
        switch(direction) {
            case LEFT: return getAllowedLeftSpeed();
            case RIGHT: return getAllowedRightSpeed();
        }
        return 0;
    }

    public boolean checkIfGrounded() {
        Rectangle lowerHitBox = entity.getLowerHitBox();
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (lowerHitBox.intersects(other.getBounds())) {
                return true;
            }
        }
        return false;
    }

//    private int getAllowedUpSpeed() {
//        return distance(other ->
//                entity.y - (other.y + other.height));
//    }

    public double getAllowedDownSpeed() {
        return getVerticalAllowedDistance(other ->
                other.y - (entity.y + entity.height));
    }

    private double getAllowedLeftSpeed() {
        return getHorizontalAllowedDistance(other ->
                entity.x - (other.x + other.width));
    }

    private double getAllowedRightSpeed() {
        return getHorizontalAllowedDistance(other ->
                other.x - (entity.x + entity.width));
    }

    private double getHorizontalAllowedDistance(DistanceCalculator calculator) {
        Rectangle collisionBound = entity.getHitBox();
        int allowedDistance = entity.getSpeed();
        return getAllowedDistance(collisionBound, allowedDistance, calculator);
    }

    private double getVerticalAllowedDistance(DistanceCalculator calculator) {
        Rectangle collisionBound = entity.getLowerHitBox();
        double allowedDistance = Math.abs(entity.getVerticalVelocity());
        return getAllowedDistance(collisionBound,allowedDistance, calculator) * entity.getVerticalDirection().getYMultiplier() * -1;
    }

    private double getAllowedDistance(Rectangle collisionBound, double allowedDistance, DistanceCalculator calculator) {
        for (StaticEntity other : CollidableRepository.getInstance()) {
            if (collisionBound.intersects(other.getBounds())) {
                allowedDistance = Math.min(allowedDistance, calculator.calculateWith(other));
            }
        }
        return allowedDistance;
    }

    private interface DistanceCalculator {
        int calculateWith(StaticEntity other);
    }
}
