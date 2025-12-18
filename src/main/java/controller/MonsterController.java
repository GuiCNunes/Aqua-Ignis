package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;
import model.entity.Slime;

import java.io.IOException;

public class MonsterController extends EntityController {

    private int speed;
    private long lastUpdate;
    private GhostController ghostController;
    private SlimeController slimeController;

    public MonsterController(Maze maze) {
        super(maze);
        this.lastUpdate = 0;
        this.ghostController = new GhostController(maze);
        this.slimeController = new SlimeController(maze);
    }

    public int getSpeed() {return speed;}



    protected Position nextPosition(Monster monster, Position target) {
        Position monsterPosition = monster.getPosition();


        int nextX = monsterPosition.getX();
        int nextY = monsterPosition.getY();
        int dx = target.getX() - nextX;
        int dy = target.getY() - nextY;

        if(Math.abs(dx) > Math.abs(dy)) {
            if(dx > 0) nextX ++;
            else nextX--;
        }
        else{
            if(dy > 0) nextY ++;
            else nextY--;
        }

        return new Position(nextX, nextY);
    }

    @Override
    public void command(Game game, GUI.operation operation, long time) throws IOException {
        if(time - lastUpdate > 500) {
            for(Monster monster : getModel().getMonsters()){
                if (monster instanceof Slime) slimeController.moveMonster((Slime) monster, monster.getPosition().getRandomNeighbour());
                else ghostController.moveMonster((Ghost) monster, monster.getPosition().getRandomNeighbour());
            }
            this.lastUpdate = time;
        }
    }
}
