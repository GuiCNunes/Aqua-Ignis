package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;

import java.io.IOException;

public class AquaController extends EntityController {

    public AquaController(Maze maze) {
        super(maze);
    }

    private void moveAqua(Position position) {
        getModel().movePlayer(getModel().getAqua(), position);
    }


    public void moveUp() {
        moveAqua(getModel().getAqua().moveUp());
    }

    public void moveDown() {
        moveAqua(getModel().getAqua().moveDown());
    }


    public void moveLeft() {
        moveAqua(getModel().getAqua().moveLeft());
    }


    public void moveRight() {
        moveAqua(getModel().getAqua().moveRight());
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        switch (op) {
            case w : moveUp(); break;
            case s: moveDown(); break;
            case a: moveLeft(); break;
            case d: moveRight(); break;
            default: break;
        }
    }
}