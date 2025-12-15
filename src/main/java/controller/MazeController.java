package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Monster;
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
        if(op == GUI.operation.quit || checkLoss()){
            System.out.println("You lost!");
            game.setState(new MenuState(new Menu()));
        }
        else if (checkWin()) {
            System.out.println("Congratulations! You win!");
            game.setState(new MenuState(new Menu()));
        }
        else if(op != GUI.operation.none) playerController.command(game, op, time);
    }

    private boolean checkWin(){
        boolean winIgnis = false;
        boolean winAqua = false;

        if(getModel().getIgnis().getPosition().getX() == getModel().getExit().getX() &&
                getModel().getIgnis().getPosition().getY() == getModel().getExit().getY()) winIgnis = true;
        if(getModel().getAqua().getPosition().getX() == getModel().getExit().getX() &&
                getModel().getAqua().getPosition().getY() == getModel().getExit().getY()) winAqua = true;

        return winIgnis && winAqua;
    }

    private boolean checkLoss(){
        Position aquaPosition = getModel().getAqua().getPosition();
        Position ignisPosition = getModel().getIgnis().getPosition();

        for(Monster monster : getModel().getMonsters()){
            Position monsterPosition = monster.getPosition();
            if(aquaPosition.equals(monsterPosition)) return true;
            if(ignisPosition.equals(monsterPosition)) return true;
        }

        return false;
    }

}