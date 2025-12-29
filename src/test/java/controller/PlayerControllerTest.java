package controller;

import controller.entity.AquaController;
import controller.entity.IgnisController;
import controller.entity.PlayerController;
import gui.GUI;
import model.Game;
import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class PlayerControllerTest {
    private Maze maze;
    private Game game;
    private PlayerController controller;
    private IgnisController ignisController;
    private AquaController aquaController;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        ignisController = mock(IgnisController.class);
        aquaController = mock(AquaController.class);

        controller = new PlayerController(maze);

        try {
            java.lang.reflect.Field ignisField = PlayerController.class.getDeclaredField("ignisController");
            ignisField.setAccessible(true);
            ignisField.set(controller, ignisController);

            java.lang.reflect.Field aquaField = PlayerController.class.getDeclaredField("aquaController");
            aquaField.setAccessible(true);
            aquaField.set(controller, aquaController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIgnisCommands() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        verify(ignisController).command(game, GUI.operation.up, 0);
        verifyNoInteractions(aquaController);

        reset(ignisController);
        controller.command(game, GUI.operation.down, 0);
        verify(ignisController).command(game, GUI.operation.down, 0);

        reset(ignisController);
        controller.command(game, GUI.operation.left, 0);
        verify(ignisController).command(game, GUI.operation.left, 0);

        reset(ignisController);
        controller.command(game, GUI.operation.right, 0);
        verify(ignisController).command(game, GUI.operation.right, 0);
    }

    @Test
    void testAquaCommands() throws IOException {
        controller.command(game, GUI.operation.w, 100);
        verify(aquaController).command(game, GUI.operation.w, 100);
        verifyNoInteractions(ignisController);

        reset(aquaController);
        controller.command(game, GUI.operation.s, 100);
        verify(aquaController).command(game, GUI.operation.s, 100);

        reset(aquaController);
        controller.command(game, GUI.operation.a, 100);
        verify(aquaController).command(game, GUI.operation.a, 100);

        reset(aquaController);
        controller.command(game, GUI.operation.d, 100);
        verify(aquaController).command(game, GUI.operation.d, 100);
    }

    @Test
    void testDefaultCommand() throws IOException {
        controller.command(game, GUI.operation.none, 100);
        verifyNoInteractions(ignisController);
        verifyNoInteractions(aquaController);

        controller.command(game, GUI.operation.quit, 100);
        verifyNoInteractions(ignisController);
        verifyNoInteractions(aquaController);
    }
}