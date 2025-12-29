package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.entity.SlimeController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class SlimeControllerTest {
    private Maze maze;
    private Game game;
    private Slime slime;
    private SlimeController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        slime = mock(Slime.class);
        controller = new SlimeController(maze);

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
    }

    @Test
    void moveSlimeToEmptySpace() {
        Position pos = new Position(5, 5);
        when(maze.isWall(5, 5)).thenReturn(false);
        controller.moveMonster(slime, pos);
        verify(slime).setPosition(pos);
    }

    @Test
    void moveSlimeToWall() {
        Position pos = new Position(5, 5);
        when(maze.isWall(5, 5)).thenReturn(true);
        controller.moveMonster(slime, pos);
        verify(slime, never()).setPosition(any());
    }

    @Test
    void commandDoesNothing() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        verifyNoInteractions(slime);
    }
}