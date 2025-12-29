package controller.entity;

import controller.EntityController;
import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Slime;
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