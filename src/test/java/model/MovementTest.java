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

    @Test
    void MovementBlockedWallTest() {
        Maze maze = new Maze(50,50);
        Position InitialPosition = maze.getAqua().getPosition();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int WallX = InitialPosition.getX() + dx;
                int WallY = InitialPosition.getY() + dy;

                if (WallX >= 0 && WallY >= 0 && WallX < 50 && WallY < 50) {
                    if(maze.isWall(WallX, WallY)) {
                        Position WallPosition = new Position(WallX, WallY);
                        maze.moveEntity(maze.getAqua(), WallPosition, false);
                        assertEquals(InitialPosition.getX(), maze.getAqua().getPosition().getX());
                        assertEquals(InitialPosition.getY(), maze.getAqua().getPosition().getY());
                        return;
                    }
                }
            }
        }
    }
}
