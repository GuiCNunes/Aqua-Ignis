package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Player;

import java.io.IOException;

public abstract class PlayerController extends GameController{

    public PlayerController(Maze maze) {
        super(maze);
    }

    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
}
