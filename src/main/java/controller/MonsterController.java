package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.entity.Monster;

import java.io.IOException;

public abstract class MonsterController extends EntityController {

    public MonsterController(Maze maze) {
        super(maze);
    }
    public abstract void moveMonster(Monster monster);

    @Override
    public void command(Game game, GUI.operation operation, long time) throws IOException {}
}
