package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.EntityController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Slime;
import java.io.IOException;

public class SlimeController extends EntityController {

    public SlimeController(Maze maze) {
        super(maze);
    }

    public void moveMonster(Slime slime, Position newPosition) {
        requestMove(slime, newPosition);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {}
}