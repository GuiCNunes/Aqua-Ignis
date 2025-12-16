package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Monster;

import java.io.IOException;

public abstract class MonsterController extends EntityController {

    private int speed;

    public MonsterController(Maze maze, int speed) {
        super(maze);
        this.speed = speed;
    }

    public int getSpeed() {return speed;}

    public abstract void moveMonster(Monster monster, Position target);

    protected Position nextPosition(Monster monster, Position target) {
        Position monsterPosition = monster.getPosition();
        int dx = target.getX() - monsterPosition.getX();
        int dy = target.getY() - monsterPosition.getY();

        int nextX = monsterPosition.getX();
        int nextY = monsterPosition.getY();

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
    public void command(Game game, GUI.operation operation, long time) throws IOException {}
}
