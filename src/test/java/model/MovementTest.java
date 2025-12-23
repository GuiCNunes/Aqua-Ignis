package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class MovementTest {
    @Test
    void MovementFreePositionTest() {
        Maze maze = new Maze(50,50);
        Position InitialPosition = maze.getAqua().getPosition();
        Position NewPosition = new Position(InitialPosition.getX()+1,InitialPosition.getY());

        if (!maze.isWall(NewPosition.getX(), NewPosition.getY())) {
            maze.moveEntity(maze.getAqua(), NewPosition, false);
            assertEquals(NewPosition.getX(), maze.getAqua().getPosition().getX());
            assertEquals(NewPosition.getY(), maze.getAqua().getPosition().getY());
        }
    }
}
