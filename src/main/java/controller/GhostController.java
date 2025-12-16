package controller;

import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;

public class GhostController extends MonsterController {

    public GhostController(Maze maze) {
        super(maze, 2);
    }

    @Override
    public void moveMonster(Monster monster, Position target){
        Ghost ghost = (Ghost) monster;

        Position position = nextPosition(monster, target);

        getModel().moveEntity(ghost,  position, true);
    }
}
