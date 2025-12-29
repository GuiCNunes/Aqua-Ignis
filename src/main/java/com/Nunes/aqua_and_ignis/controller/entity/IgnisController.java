package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.EntityController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import java.io.IOException;

public class IgnisController extends EntityController {
    public IgnisController(Maze maze) { super(maze); }
    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if (op == GUI.operation.up) requestMove(getModel().getIgnis(), getModel().getIgnis().getPosition().getUp());
        if (op == GUI.operation.down) requestMove(getModel().getIgnis(), getModel().getIgnis().getPosition().getDown());
        if (op == GUI.operation.left) requestMove(getModel().getIgnis(), getModel().getIgnis().getPosition().getLeft());
        if (op == GUI.operation.right) requestMove(getModel().getIgnis(), getModel().getIgnis().getPosition().getRight());
    }
}