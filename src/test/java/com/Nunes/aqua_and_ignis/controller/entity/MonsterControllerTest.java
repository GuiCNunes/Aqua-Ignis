package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.entity.MonsterController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Ghost;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

class MonsterControllerTest {
    private Maze maze;
    private Game game;
    private MonsterController controller;
    private Position pos;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        pos = mock(Position.class);
        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
        when(pos.getRandomNeighbour()).thenReturn(new Position(1, 1));
        controller = new MonsterController(maze);
    }

    @Test
    void testCommandTimerExactBoundary() throws IOException {
        Monster monster = mock(Monster.class);
        when(monster.getPosition()).thenReturn(pos);
        when(maze.getMonsters()).thenReturn(List.of(monster));

        controller.command(game, GUI.operation.none, 500);
        verify(monster, never()).getPosition();

        controller.command(game, GUI.operation.none, 501);
        verify(monster, times(1)).getPosition();
    }

    @Test
    void testGhostMoveExecution() throws IOException {
        Ghost ghost = mock(Ghost.class);
        when(ghost.getPosition()).thenReturn(pos);
        when(maze.getMonsters()).thenReturn(List.of(ghost));

        controller.command(game, GUI.operation.none, 501);

        verify(ghost, atLeastOnce()).setPosition(any(Position.class));
    }

    @Test
    void testSlimeMoveExecution() throws IOException {
        Slime slime = mock(Slime.class);
        when(slime.getPosition()).thenReturn(pos);
        when(maze.getMonsters()).thenReturn(List.of(slime));
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);

        controller.command(game, GUI.operation.none, 501);

        verify(slime, atLeastOnce()).setPosition(any(Position.class));
    }

    @Test
    void testTimerResetAfterUpdate() throws IOException {
        Slime slime = mock(Slime.class);
        when(slime.getPosition()).thenReturn(pos);
        when(maze.getMonsters()).thenReturn(List.of(slime));

        controller.command(game, GUI.operation.none, 501);
        verify(slime, times(1)).getPosition();

        controller.command(game, GUI.operation.none, 1000);
        verify(slime, times(1)).getPosition();

        controller.command(game, GUI.operation.none, 1002);
        verify(slime, times(2)).getPosition();
    }
}