package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.menu.Menu;
import states.MenuState;

import java.io.IOException;

public class MazeController extends GameController {
    private PlayerController playerController;

    public MazeController(Maze maze) {
        super(maze);
        this.playerController = new PlayerController(maze);
    }

    public void command(Game game, GUI.operation op, long time) throws IOException {
        if(op == GUI.operation.quit || getModel().checkLoss()){
            System.out.println("You lost!");
            game.setState(new MenuState(new Menu()));
        }
        else if (getModel().checkWin(getModel().getAqua().getPosition()) && getModel().checkWin(getModel().getIgnis().getPosition())) {
            System.out.println("Congratulations! You win!");
            game.setState(new MenuState(new Menu()));
        }
        else if(op != GUI.operation.none) playerController.command(game, op, time);
    }

}