package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.view.mazeView.FieldOfView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FieldOfViewTest {
    private FieldOfView fov;
    private Maze maze;

    @BeforeEach
    void setUp() {
        fov = new FieldOfView(5);
        maze = mock(Maze.class);
        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
    }

    @Test
    void testGridSize() {
        boolean[][] visible = fov.computeVisible(maze, new Position(5, 5));
        assertEquals(10, visible.length);
        assertEquals(10, visible[0].length);
    }

    @Test
    void testOutOfRange() {
        assertFalse(fov.isVisible(maze, new Position(0, 0), 5, 5));
    }

    @Test
    void testVisiblePath() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 2, 0));
    }

    @Test
    void testWallInPath() {
        when(maze.isWall(0, 0)).thenReturn(false);
        when(maze.isWall(1, 0)).thenReturn(true);
        assertFalse(fov.isVisible(maze, new Position(0, 0), 2, 0));
    }

    @Test
    void testStartAtWall() {
        when(maze.isWall(0, 0)).thenReturn(true);
        when(maze.isWall(1, 0)).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 1, 0));
    }

    @Test
    void testDiagonal() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 2, 2));
    }

    @Test
    void testMoveX() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 2, 1));
    }

    @Test
    void testMoveY() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 1, 2));
    }

    @Test
    void testReverse() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(5, 5), 3, 3));
    }

    @Test
    void testWallInMiddle() {
        when(maze.isWall(0, 0)).thenReturn(false);
        when(maze.isWall(1, 1)).thenReturn(true);
        assertFalse(fov.isVisible(maze, new Position(0, 0), 2, 2));
    }

    @Test
    void testConditionBranches() {
        Position start = new Position(1, 1);

        when(maze.isWall(1, 1)).thenReturn(true);
        when(maze.isWall(2, 1)).thenReturn(false);
        assertTrue(fov.isVisible(maze, start, 2, 1));

        when(maze.isWall(1, 1)).thenReturn(false);
        when(maze.isWall(2, 1)).thenReturn(true);
        assertFalse(fov.isVisible(maze, start, 3, 1));

        when(maze.isWall(1, 1)).thenReturn(false);
        when(maze.isWall(1, 2)).thenReturn(true);
        assertFalse(fov.isVisible(maze, start, 1, 3));

        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, start, 2, 1));
    }

    @Test
    void testComputeVisibleLoops() {
        when(maze.getWidth()).thenReturn(2);
        when(maze.getHeight()).thenReturn(2);
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        boolean[][] visible = fov.computeVisible(maze, new Position(0, 0));

        assertTrue(visible[0][0]);
        assertTrue(visible[1][0]);
        assertTrue(visible[0][1]);
        assertTrue(visible[1][1]);
    }

    @Test
    void testIsVisibleRadiusBoundary() {
        Position from = new Position(5, 5);
        int r = 5;
        FieldOfView fovR = new FieldOfView(r);

        assertTrue(fovR.isVisible(maze, from, 5, 10));
        assertFalse(fovR.isVisible(maze, from, 5, 11));
    }

    @Test
    void testStepConditionBoundaries() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        assertTrue(fov.isVisible(maze, new Position(5, 5), 6, 6));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 4, 4));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 5, 6));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 5, 4));
    }

    @Test
    void testFovLogic() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        assertTrue(fov.isVisible(maze, new Position(0, 0), 4, 2));
        assertTrue(fov.isVisible(maze, new Position(0, 0), 2, 4));
    }

    @Test
    void testWallDetection() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        when(maze.isWall(1, 1)).thenReturn(true);

        assertFalse(fov.isVisible(maze, new Position(0, 0), 2, 2));
    }

    @Test
    void testStepXBoundary() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        assertTrue(fov.isVisible(maze, new Position(5, 5), 5, 5));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 6, 5));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 4, 5));
    }

    @Test
    void testStepYBoundary() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        assertTrue(fov.isVisible(maze, new Position(5, 5), 5, 6));
        assertTrue(fov.isVisible(maze, new Position(5, 5), 5, 4));
    }

    @Test
    void testDifGreaterBoundary() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        int x0 = 0, y0 = 0;
        int x = 1, y = 2;

        assertTrue(fov.isVisible(maze, new Position(x0, y0), x, y));
    }

    @Test
    void testDifLessBoundary() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        int x0 = 0, y0 = 0;
        int x = 2, y = 1;

        assertTrue(fov.isVisible(maze, new Position(x0, y0), x, y));
    }

    @Test
    void testExactDiagonal() {
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
        assertTrue(fov.isVisible(maze, new Position(0, 0), 1, 1));
    }

}