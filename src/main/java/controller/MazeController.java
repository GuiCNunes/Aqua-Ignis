package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;
import model.entity.Slime;
import model.menu.Menu;
import states.MenuState;

import java.io.IOException;
import java.util.Random;

public class MazeController extends GameController {
    private PlayerController playerController;
    private MonsterController monsterController;
    private long lastSpawnTime = 0;
    private long spawnInterval = 100;

    public MazeController(Maze maze) {
        super(maze);
        this.playerController = new PlayerController(maze);
        this.monsterController = new MonsterController(maze);

    }

    public void command(Game game, GUI.operation op, long time) throws IOException {

        spawnMonsters(time);
        monsterController.command(game,op, time);



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

    private void spawnMonsters(long time){
        if(time - lastSpawnTime >= spawnInterval  && getModel().getMonsters().size() < 1000){
            Position spawn = getModel().getFreePosition();

            Random random = new Random();

            if(random.nextBoolean()) getModel().addMonster(new Slime(spawn.getX(), spawn.getY()));
            else getModel().addMonster(new Ghost(spawn.getX(), spawn.getY()));

            lastSpawnTime = time;
        }
    }

}