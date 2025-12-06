package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Player;

import java.io.IOException;

public class MazeController extends GameController {
    private PlayerController playerController;

    public MazeController(Maze maze) {
        super(maze);
        this.playerController = new PlayerController(maze);
    }

    public void command(Game game, GUI.operation op) throws IOException {
        if(op == GUI.operation.quit || getModel().checkLoss()){
            game.getGui().stopScreen();
        }
        else if(op != GUI.operation.none) playerController.command(game, op);
    }

}