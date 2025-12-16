package controller;

import gui.GUI;
import model.Game;
import model.Maze;

public class MonsterManagerController extends GameController {

    public MonsterManagerController(Maze maze) {
        super(maze);
    }

    @Override
    public void command(Game game, GUI.operation operation, long time){}
}
