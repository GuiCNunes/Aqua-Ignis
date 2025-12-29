package controller.entity;

import controller.EntityController;
import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;
import model.entity.Slime;
import java.io.IOException;

public class MonsterController extends EntityController {
    private long lastUpdate = 0;
    private final GhostController ghostController;
    private final SlimeController slimeController;

    public MonsterController(Maze maze) {
        super(maze);
        this.ghostController = new GhostController(maze);
        this.slimeController = new SlimeController(maze);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if (time - lastUpdate > 500) {
            for (Monster monster : getModel().getMonsters()) {
                Position next = monster.getPosition().getRandomNeighbour();

                if (monster instanceof Ghost) {
                    ghostController.moveMonster((Ghost) monster, next);
                } else if (monster instanceof Slime) {
                    slimeController.moveMonster((Slime) monster, next);
                }
            }
            lastUpdate = time;
        }
    }
}