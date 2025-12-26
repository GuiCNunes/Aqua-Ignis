package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FogOfWarTest {

    @Test
    void InitialMemoryEmptyTest() {
        Maze maze = new Maze(50,50);
        assertFalse(maze.isMemorized(5,5));
    }

    @Test
    void UpdateMemoryTest() {
        Maze maze = new Maze(50,50);
        maze.updateMemory(5,5);
        assertTrue(maze.isMemorized(5,5));
    }

    @Test
    void MemoryOutOfBoundsTest() {
        Maze maze = new Maze(50,50);
        maze.updateMemory(-1, -1);
        assertFalse(maze.isMemorized(50,50));
    }
}
