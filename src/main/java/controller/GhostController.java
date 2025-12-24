package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
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