package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.EntityController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import java.io.IOException;

public class AquaController extends EntityController {
    public AquaController(Maze maze) { super(maze); }
    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if (op == GUI.operation.w) requestMove(getModel().getAqua(), getModel().getAqua().getPosition().getUp());
        if (op == GUI.operation.s) requestMove(getModel().getAqua(), getModel().getAqua().getPosition().getDown());
        if (op == GUI.operation.a) requestMove(getModel().getAqua(), getModel().getAqua().getPosition().getLeft());
        if (op == GUI.operation.d) requestMove(getModel().getAqua(), getModel().getAqua().getPosition().getRight());
    }
}