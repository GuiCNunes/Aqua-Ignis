package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;

import java.io.IOException;

public class IgnisController extends EntityController {

    public IgnisController(Maze maze) {
        super(maze);
    }

    private void moveIgnis(Position position) {
        getModel().movePlayer(getModel().getIgnis(),  position);
    }


    public void moveUp() {
        moveIgnis(getModel().getIgnis().moveUp());
    }

    public void moveDown() {
        moveIgnis(getModel().getIgnis().moveDown());
    }

    public void moveLeft() {
        moveIgnis(getModel().getIgnis().moveLeft());
    }

    public void moveRight() {
        moveIgnis(getModel().getIgnis().moveRight());
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException{
        switch (op) {
            case up : moveUp(); break;
            case down: moveDown(); break;
            case left: moveLeft(); break;
            case right: moveRight(); break;
            default: break;
        }
    }
}