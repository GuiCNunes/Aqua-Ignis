package controller;

import model.Maze;
import model.entity.Monster;

public class GhostController extends MonsterController {

    public GhostController(Maze maze) {
        super(maze);
    }

    @Override
    public void moveMonster(Monster monster){}
}
