package model;
/*
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void TestMazeCreation() {
        Maze maze = new Maze(20, 50);
        assertEquals(20, maze.getWidth());
        assertEquals(50, maze.getHeight());
    }

    @Test
    void testPlayersCreation() {
        Maze maze = new Maze(30, 50);
        assertNotNull(maze.getAqua());
        assertNotNull(maze.getIgnis());
    }

    @Test
    void TestMonsterCreation() {
        Maze maze = new Maze(20, 50);
        assertNotNull(maze.getMonsters());
        assertEquals(2, maze.getMonsters().size());
    }

    @Test
    void TestExitExists() {
        Maze maze = new Maze(20, 50);
        assertNotNull(maze.getExit());
    }

    @Test
    void TestWinExit() {
        Maze maze = new Maze(20, 50);
        Position exitPosition = maze.getExit();
        assertTrue(maze.checkWin(exitPosition));
    }

    @Test
    void TestWinNotExit() {
        Maze maze = new Maze(20, 50);
        Position randomPosition = new Position(7,6);
        assertFalse(maze.checkWin(randomPosition));
    }

    @Test
    void TestWallsCreation() {
        Maze maze = new Maze(50, 50);
        assertTrue(maze.isWall(0,0));
        assertTrue(maze.isWall(49,0));
        assertTrue(maze.isWall(0,49));
        assertTrue(maze.isWall(49,49));
    }

    @Test
    void testPlayerStarPosition() {
        Maze maze = new Maze(20, 50);
        assertEquals(11, maze.getIgnis().getPosition().getX());
        assertEquals(10, maze.getIgnis().getPosition().getY());
        assertEquals(10, maze.getAqua().getPosition().getX());
        assertEquals(10, maze.getAqua().getPosition().getY());
    }

    @Test
    void TestMove() {
        Maze maze = new Maze(40, 50);
        Position startposition = maze.getAqua().getPosition();
        int startX = startposition.getX();
        int startY = startposition.getY();

        assertFalse(maze.isWall(startX,startY));
    }

    @Test
    void TestMoveOutBounds() {
        Maze maze = new Maze(60, 50);
        Position OutBounds = new  Position(-1,10);
        assertTrue(OutBounds.getX() < 0);
    }

    @Test
    void TestWallsBlockMov() {
        Maze maze = new Maze(20, 50);
        Position exitPosition = maze.getExit();

        for (int x = 0; x < maze.getWidth(); x++) {
            assertTrue(maze.isWall(x,0));
            if (x != exitPosition.getX() || (maze.getHeight() - 1) != exitPosition.getY()) {
                assertTrue(maze.isWall(x,maze.getHeight()-1));
            }
        }
        for (int y = 0; y < maze.getHeight(); y++) {
            assertTrue(maze.isWall(0,y));
            assertTrue(maze.isWall(maze.getWidth()-1, y));
        }
    }
}
*/