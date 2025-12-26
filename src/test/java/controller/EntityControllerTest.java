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
    void testMoveOutOfBoundsNegativeX() {
        Entity entity = mock(Entity.class);
        Position out = new Position(-1, 5);
        controller.requestMove(entity, out);
        verify(entity, never()).setPosition(any());
    }

    @Test
    void testMoveOutOfBoundsUpperX() {
        Entity entity = mock(Entity.class);
        Position out = new Position(10, 5);
        controller.requestMove(entity, out);
        verify(entity, never()).setPosition(any());
    }

    @Test
    void testMoveOutOfBoundsNegativeY() {
        Entity entity = mock(Entity.class);
        Position out = new Position(5, -1);
        controller.requestMove(entity, out);
        verify(entity, never()).setPosition(any());
    }

    @Test
    void testMoveOutOfBoundsUpperY() {
        Entity entity = mock(Entity.class);
        Position out = new Position(5, 10);
        controller.requestMove(entity, out);
        verify(entity, never()).setPosition(any());
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
        when(maze.isWall(5, 5)).thenReturn(true);
        controller.requestMove(slime, position);
        verify(slime, never()).setPosition(any());
    }
}