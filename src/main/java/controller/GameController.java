package controller;

import model.Maze;

public abstract class GameController extends Controller<Maze> {
    public GameController(Maze maze) {
        super(maze);
    }
}
