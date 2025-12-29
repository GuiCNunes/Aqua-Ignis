package controller.menu;

import gui.GUI;
import model.Game;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import states.GameState;
import states.HelpState;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    private Menu menu;
    private Game game;
    private MenuController controller;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        controller = new MenuController(menu);
    }

    @Test
    void testCommandUp() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        verify(menu).previousEntry();
    }

    @Test
    void testCommandDown() throws IOException {
        controller.command(game, GUI.operation.down, 0);
        verify(menu).nextEntry();
    }

    @Test
    void testSelectExit() throws IOException {
        when(menu.isSelectedExit()).thenReturn(true);
        controller.command(game, GUI.operation.select, 0);
        verify(game).setState(null);
    }

    @Test
    void testSelectStart() throws IOException {
        when(menu.isSelectedExit()).thenReturn(false);
        when(menu.isSelectedStart()).thenReturn(true);
        controller.command(game, GUI.operation.select, 0);
        verify(game).setState(any(GameState.class));
    }

    @Test
    void testSelectSize() throws IOException {
        when(menu.isSelectedExit()).thenReturn(false);
        when(menu.isSelectedStart()).thenReturn(false);
        when(menu.isSelectedSize()).thenReturn(true);
        controller.command(game, GUI.operation.select, 0);
        verify(game).recreateGUI();
    }

    @Test
    void testSelectHelp() throws IOException {
        when(menu.isSelectedExit()).thenReturn(false);
        when(menu.isSelectedStart()).thenReturn(false);
        when(menu.isSelectedSize()).thenReturn(false);
        when(menu.isSelectedHelp()).thenReturn(true);
        controller.command(game, GUI.operation.select, 0);
        verify(game).setState(any(HelpState.class));
    }

    @Test
    void testSelectNone() throws IOException {
        when(menu.isSelectedExit()).thenReturn(false);
        when(menu.isSelectedStart()).thenReturn(false);
        when(menu.isSelectedSize()).thenReturn(false);
        when(menu.isSelectedHelp()).thenReturn(false);
        controller.command(game, GUI.operation.select, 0);
        verify(game, never()).setState(any());
        verify(game, never()).recreateGUI();
    }

    @Test
    void testQuit() throws IOException {
        controller.command(game, GUI.operation.quit, 0);
        verify(game).setState(null);
    }

    @Test
    void testDefaultBranch() throws IOException {
        controller.command(game, GUI.operation.none, 0);
        verifyNoInteractions(game);
        verifyNoInteractions(menu);
    }
}