package controller.menu;

import gui.GUI;
import model.Game;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import states.MenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverControllerTest {
    private Menu menu;
    private Game game;
    private GameOverController controller;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        controller = new GameOverController(menu);
    }

    @Test
    void testCommandSelect() throws IOException {
        controller.command(game, GUI.operation.select, 0);
        verify(game).setState(any(MenuState.class));
    }

    @Test
    void testCommandOtherOperations() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        controller.command(game, GUI.operation.down, 0);
        controller.command(game, GUI.operation.none, 0);

        verify(game, never()).setState(any());
    }
}