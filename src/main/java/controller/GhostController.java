package controller;

import gui.GUI;
import model.Entity;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;

import java.io.IOException;

public class GhostController extends EntityController {

    public GhostController(Maze maze) {
        super(maze);
    }


    public void moveMonster(Ghost ghost, Position newPosition) {
        getModel().moveEntity(ghost,  newPosition, true);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {}
}
