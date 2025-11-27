package controller;

import com.googlecode.lanterna.input.KeyStroke;
import model.Maze;

public class MazeController {
    private Maze maze;

    public MazeController(Maze maze) {
        this.maze = maze;
    }

    public void processKey(KeyStroke key){
        maze.processKey(key);
    }

}