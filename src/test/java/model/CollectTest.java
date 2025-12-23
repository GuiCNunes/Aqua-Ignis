package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CollectTest {

    @Test
    void TestCollectStar() {
        Maze maze = new Maze(50,50);
        int InitialStars = maze.getCollectedStars();
        assertEquals(0, InitialStars);

        if (!maze.getStars().isEmpty()) {
            Position starPosition = maze.getStars().get(0).getPosition();
            maze.moveEntity(maze.getAqua(), starPosition, false);
            assertEquals(1, maze.getCollectedStars());
        }
    }

    @Test
    void TestCollectKeys() {
        Maze maze = new Maze(50,50);
        assertFalse(maze.isExitOpen());
        assertNotNull(maze.getKey());
        Position keyPosition = maze.getKey().getPosition();
        maze.moveEntity(maze.getIgnis(), keyPosition, false);
        assertTrue(maze.isExitOpen());
        assertNull(maze.getKey());
    }
}
