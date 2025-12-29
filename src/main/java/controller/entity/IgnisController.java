package controller.entity;

import controller.EntityController;
import gui.GUI;
import model.Game;
import model.Maze;
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