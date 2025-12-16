package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Monster;
import model.entity.Slime;

import java.io.IOException;

public class SlimeController extends EntityController {


    public SlimeController(Maze maze) {
        super(maze);
    }


    public void moveMonster(Slime slime, Position newPosition) {
        getModel().moveEntity(slime, newPosition, false);
    }
    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {}
}
