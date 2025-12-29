package controller;

import controller.entity.GhostController;
import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GhostControllerTest {
    private Maze maze;
    private Game game;
    private Ghost ghost;
    private GhostController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        ghost = mock(Ghost.class);
        controller = new GhostController(maze);

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
    }

    @Test
    void moveGhostThroughWall() {
        Position pos = new Position(5, 5);
        when(maze.isWall(5, 5)).thenReturn(true);
        controller.moveMonster(ghost, pos);
        verify(ghost).setPosition(pos);
    }

    @Test
    void moveGhostOutOfBounds() {
        Position pos = new Position(-1, 5);
        controller.moveMonster(ghost, pos);
        verify(ghost, never()).setPosition(any());
    }

    @Test
    void commandDoesNothing() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        verifyNoInteractions(ghost);
    }
}