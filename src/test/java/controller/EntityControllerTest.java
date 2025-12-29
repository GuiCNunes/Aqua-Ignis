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

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        controller = new EntityController(maze) {
            @Override
            public void command(model.Game game, gui.GUI.operation op, long time) {}
        };

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
    }

    @Test
    void testMoveAtBoundaries() {
        Entity entity = mock(Entity.class);

        Position zeroX = new Position(0, 5);
        controller.requestMove(entity, zeroX);
        verify(entity).setPosition(zeroX);

        Position maxX = new Position(9, 5);
        controller.requestMove(entity, maxX);
        verify(entity).setPosition(maxX);

        Position zeroY = new Position(5, 0);
        controller.requestMove(entity, zeroY);
        verify(entity).setPosition(zeroY);

        Position maxY = new Position(5, 9);
        controller.requestMove(entity, maxY);
        verify(entity).setPosition(maxY);
    }

    @Test
    void testMoveOutOfBoundsEdges() {
        Entity entity = mock(Entity.class);

        controller.requestMove(entity, new Position(-1, 5));
        controller.requestMove(entity, new Position(10, 5));
        controller.requestMove(entity, new Position(5, -1));
        controller.requestMove(entity, new Position(5, 10));

        verify(entity, never()).setPosition(any());
    }

    @Test
    void testWallCollisions() {
        Entity normal = mock(Slime.class);
        Ghost ghost = mock(Ghost.class);
        Position wallPos = new Position(5, 5);

        when(maze.isWall(5, 5)).thenReturn(true);

        controller.requestMove(normal, wallPos);
        verify(normal, never()).setPosition(any());

        controller.requestMove(ghost, wallPos);
        verify(ghost).setPosition(wallPos);
    }

    @Test
    void testEmptySpaceMove() {
        Entity entity = mock(Entity.class);
        Position pos = new Position(2, 2);
        when(maze.isWall(2, 2)).thenReturn(false);

        controller.requestMove(entity, pos);
        verify(entity).setPosition(pos);
    }
}