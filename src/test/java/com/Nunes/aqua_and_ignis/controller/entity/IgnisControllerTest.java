package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.entity.IgnisController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Ignis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class IgnisControllerTest {
    private Maze maze;
    private Game game;
    private Ignis ignis;
    private IgnisController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        ignis = mock(Ignis.class);
        controller = new IgnisController(maze);

        when(maze.getIgnis()).thenReturn(ignis);
        when(ignis.getPosition()).thenReturn(new Position(5, 5));

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
    }

    @Test
    void ignisCommandUp() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        verify(ignis).setPosition(any(Position.class));
    }

    @Test
    void ignisCommandDown() throws IOException {
        controller.command(game, GUI.operation.down, 0);
        verify(ignis).setPosition(any(Position.class));
    }

    @Test
    void ignisCommandLeft() throws IOException {
        controller.command(game, GUI.operation.left, 0);
        verify(ignis).setPosition(any(Position.class));
    }

    @Test
    void ignisCommandRight() throws IOException {
        controller.command(game, GUI.operation.right, 0);
        verify(ignis).setPosition(any(Position.class));
    }
}