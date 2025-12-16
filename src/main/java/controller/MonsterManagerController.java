package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;
import model.entity.Slime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MonsterManagerController extends GameController {

    private SlimeController slimeController;
    private GhostController ghostController;
    private Map<Monster, Long> lastMoveTimes = new HashMap<>();

    public MonsterManagerController(Maze maze) {
        super(maze);
        this.slimeController = new SlimeController(maze);
        this.ghostController = new GhostController(maze);
    }

    private Position findPlayer(Position monsterPosition){
        Position aquaPosition = getModel().getAqua().getPosition();
        Position ignisPosition = getModel().getIgnis().getPosition();

        int dAquaX = (aquaPosition.getX() - monsterPosition.getX());
        int dAquaY = (aquaPosition.getY() - monsterPosition.getY());
        int dIgnisX = (ignisPosition.getX() - monsterPosition.getX());
        int dIgnisY = (ignisPosition.getY() - monsterPosition.getY());

        int dAqua = dAquaX * dAquaX + dAquaY * dAquaY;
        int dIgnis = dIgnisX * dIgnisX + dIgnisY * dIgnisY;

        if(dAqua > dIgnis) return ignisPosition;
        return aquaPosition;
    }

    public void update(Game game, long time) {
        for(Monster monster : getModel().getMonsters()){

            MonsterController monsterController;

            if(monster instanceof Ghost) monsterController = ghostController;
            else if(monster instanceof Slime) monsterController = slimeController;
            else continue;

            long lastMove = lastMoveTimes.getOrDefault(monster, time);
            long delay = 1000/monsterController.getSpeed();

            if(time - lastMove >= delay) {
                Position target = findPlayer(monster.getPosition());
                monsterController.moveMonster(monster, target);

                lastMoveTimes.put(monster, time);
            }

        }
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException{
        update(game, time);
    }
}
