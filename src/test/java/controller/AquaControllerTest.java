package controller;

import controller.entity.AquaController;
import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Aqua;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class AquaControllerTest {
    private Maze maze;
    private Game game;
    private Aqua aqua;
    private AquaController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        aqua = mock(Aqua.class);
        controller = new AquaController(maze);

        when(maze.getAqua()).thenReturn(aqua);
        when(aqua.getPosition()).thenReturn(new Position(5, 5));

        when(maze.getWidth()).thenReturn(10);
        when(maze.getHeight()).thenReturn(10);
        when(maze.isWall(anyInt(), anyInt())).thenReturn(false);
    }

    @Test
    void aquaCommandW() throws IOException {
        controller.command(game, GUI.operation.w, 0);
        verify(aqua).setPosition(any(Position.class));
    }

    @Test
    void aquaCommandS() throws IOException {
        controller.command(game, GUI.operation.s, 0);
        verify(aqua).setPosition(any(Position.class));
    }

    @Test
    void aquaCommandA() throws IOException {
        controller.command(game, GUI.operation.a, 0);
        verify(aqua).setPosition(any(Position.class));
    }

    @Test
    void aquaCommandD() throws IOException {
        controller.command(game, GUI.operation.d, 0);
        verify(aqua).setPosition(any(Position.class));
    }
}