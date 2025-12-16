package controller;

import model.Maze;
import model.Position;
import model.entity.Monster;
import model.entity.Slime;

public class SlimeController extends MonsterController {

    public SlimeController(Maze maze, int speed) {
        super(maze, 1);
    }

    @Override
    public void moveMonster(Monster monster, Position target) {
        Slime slime = (Slime) monster;

        Position position = nextPosition(monster, target);

        getModel().moveEntity(monster, position, false);
    }
}
