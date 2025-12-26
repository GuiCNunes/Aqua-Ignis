package controller;

import model.Maze;
import model.Position;
import model.Entity;
import model.entity.Ghost;

public abstract class EntityController extends GameController {
    public EntityController(Maze maze) {
        super(maze);
    }

    protected void requestMove(Entity entity, Position newPos) {
        if (newPos.getX() < 0 || newPos.getX() >= getModel().getWidth() ||
                newPos.getY() < 0 || newPos.getY() >= getModel().getHeight()) return;

        if (entity instanceof Ghost || !getModel().isWall(newPos.getX(), newPos.getY())) {
            entity.setPosition(newPos);
        }
    }
}