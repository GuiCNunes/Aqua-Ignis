package controller;

import model.Entity;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EntityControllerTest {
    private Maze maze;
    private EntityController controller;
    private Position position;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        controller = new EntityController(maze) {
            @Override
            public void command(model.Game game, gui.GUI.operation op, long time) {}
        };
        position = new Position(5, 5);

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
    }

    @Test
    void testMoveToEmptySpace() {
        Entity entity = mock(Entity.class);
        Position pos = new Position(5, 5);

        when(maze.isWall(5, 5)).thenReturn(false);

        controller.requestMove(entity, pos);

        verify(entity, times(1)).setPosition(pos);
    }

    @Test
    void testMoveOutOfBoundsNegative() {
        Entity entity = mock(Entity.class);
        Position outOfBounds = new Position(-1, 0);

        controller.requestMove(entity, outOfBounds);

        verify(entity, never()).setPosition(outOfBounds);
    }

    @Test
    void requestMoveNormalEntityToWall() {
        Entity entity = mock(Entity.class);
        when(maze.isWall(5, 5)).thenReturn(true);

        controller.requestMove(entity, position);

        verify(entity, never()).setPosition(any());
    }

    @Test
    void requestMoveGhostToWall() {
        Ghost ghost = mock(Ghost.class);
        when(maze.isWall(5, 5)).thenReturn(true);

        controller.requestMove(ghost, position);

        verify(ghost, times(1)).setPosition(position);
    }

    @Test
    void requestMoveSlimeToWall() {
        Slime slime = mock(Slime.class);
        Position wallPos = new Position(5, 5);

        when(maze.isWall(5, 5)).thenReturn(true);

        controller.requestMove(slime, wallPos);

        verify(slime, never()).setPosition(wallPos);
    }

    @Test
    void requestMoveOutOfBoundsX() {
        Entity entity = mock(Entity.class);
        Position out = new Position(-1, 5);

        controller.requestMove(entity, out);

        verify(entity, never()).setPosition(any());
    }

    @Test
    void requestMoveOutOfBoundsY() {
        Entity entity = mock(Entity.class);
        Position out = new Position(5, 10);

        controller.requestMove(entity, out);

        verify(entity, never()).setPosition(any());
    }

    @Test
    void requestMoveToEmptySpace() {
        Entity entity = mock(Entity.class);
        when(maze.isWall(5, 5)).thenReturn(false);

        controller.requestMove(entity, position);

        verify(entity, times(1)).setPosition(position);
    }
}