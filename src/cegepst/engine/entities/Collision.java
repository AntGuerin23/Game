package cegepst.engine.entities;

import cegepst.engine.controls.Direction;
import cegepst.engine.repositories.CollidableRepository;
import cegepst.mainGame.MainGame;
import cegepst.mainGame.worlds.World;

import java.awt.*;
import java.util.Map;

public class Collision {

    private final MovableEntity entity;

    public Collision(MovableEntity entity) {
        this.entity = entity;
    }

    public double getHorizontalAllowedSpeed(Direction direction) {
        switch(direction) {
            case LEFT: return getAllowedLeftSpeed();
            case RIGHT: return getAllowedRightSpeed();
        }
        return 0;
    }

    public double getVerticalAllowedSpeed(Direction direction) {
        switch(direction) {
            case UP: return getAllowedUpSpeed();
            case DOWN: return getAllowedDownSpeed();
        }
        return 0;
    }

    public boolean checkIfVerticallyStuck(boolean checkingFloor) {
        Rectangle hitBox = (checkingFloor) ? entity.getLowerHitBox() : entity.getUpperHitBox();
        for (Map.Entry<StaticEntity, World> entry : CollidableRepository.getInstance().getRepository()) {
            StaticEntity other = entry.getKey();
            if (hitBox.intersects(other.getBounds()) && entry.getValue() == MainGame.getInstance().getCurrentWorld() && MainGame.getInstance().isPlayerNear(other)) {
                return true;
            }
        }
        return false;
    }

    private double getAllowedUpSpeed() {
        return getVerticalAllowedDistance(other ->
                entity.y - (other.y + other.height));
    }

    private double getAllowedDownSpeed() {
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
        Rectangle collisionBound = entity.getHorizontalHitBox();
        double allowedDistance = entity.getSpeed();
        return getAllowedDistance(collisionBound, allowedDistance, calculator);
    }

    private double getVerticalAllowedDistance(DistanceCalculator calculator) {
        Rectangle collisionBound = entity.getVerticalHitBox();
        double allowedDistance = Math.abs(entity.getVerticalVelocity());
        return getAllowedDistance(collisionBound,allowedDistance, calculator) * entity.getVerticalDirection().getYMultiplier() * -1;
    }

    private double getAllowedDistance(Rectangle collisionBound, double allowedDistance, DistanceCalculator calculator) {
        for (Map.Entry<StaticEntity, World> entry : CollidableRepository.getInstance().getRepository()) {
            StaticEntity other = entry.getKey();
            if (collisionBound.intersects(other.getBounds()) && entry.getValue() == MainGame.getInstance().getCurrentWorld() && MainGame.getInstance().isPlayerNear(other)) {
                allowedDistance = Math.min(allowedDistance, calculator.calculateWith(other));
            }
        }
        return allowedDistance;
    }

    private interface DistanceCalculator {
        int calculateWith(StaticEntity other);
    }
}
