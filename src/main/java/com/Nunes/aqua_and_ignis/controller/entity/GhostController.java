package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.EntityController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Ghost;
import java.io.IOException;

public class GhostController extends EntityController {

    public GhostController(Maze maze) {
        super(maze);
    }

    public void moveMonster(Ghost ghost, Position newPosition) {
        requestMove(ghost, newPosition);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {}
}