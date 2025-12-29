package com.Nunes.aqua_and_ignis.controller;

import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.Entity;
import com.Nunes.aqua_and_ignis.model.entity.Ghost;

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