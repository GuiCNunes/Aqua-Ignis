package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    @Test
    void GeneratePlayersTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getAqua());
        assertNotNull(maze.getIgnis());
    }

    @Test
    void GenerateExitTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getExit());
    }
    @Test
    void GenerateKeyTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getKey());
    }
    @Test
    void GenerateStarsTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getStars());
    }
    @Test
    void PlayerPositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int centerX = 25;
        int centerY = 25;

        assertEquals(centerX - 1, maze.getIgnis().getPosition().getX());
        assertEquals(centerY, maze.getIgnis().getPosition().getY());
        assertEquals(centerX + 1, maze.getAqua().getPosition().getX());
        assertEquals(centerY, maze.getAqua().getPosition().getY());
    }
    @Test
    void ExitNotWallTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        assertFalse(maze.isWall(exitPosition.getX(),  exitPosition.getY()));
    }
    @Test
    void GetFreePositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position FreePosition = generatorTest.getFreePosition(maze);
        assertFalse(maze.isWall(FreePosition.getX(), FreePosition.getY()));
    }

    @Test
    void MazeHasPathsTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int FreeSpace = 0;
        for (int y = 0; y < maze.getHeight(); y++){
            for (int x = 0; x < maze.getWidth(); x++){
                if (!maze.isWall(x, y)){
                    FreeSpace ++;
                }
            }

        }
    }
}
