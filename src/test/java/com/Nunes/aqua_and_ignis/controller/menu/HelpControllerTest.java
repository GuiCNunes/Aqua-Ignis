package com.Nunes.aqua_and_ignis.controller.menu;

import com.Nunes.aqua_and_ignis.controller.menu.HelpController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.states.MenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HelpControllerTest {
    private Menu menu;
    private Game game;
    private HelpController controller;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        controller = new HelpController(menu);
    }

    @Test
    void testCommandSelect() throws IOException {
        controller.command(game, GUI.operation.select, 0);
        verify(game).setState(any(MenuState.class));
    }

    @Test
    void testCommandOtherOperations() throws IOException {
        controller.command(game, GUI.operation.up, 0);
        controller.command(game, GUI.operation.quit, 0);

        verify(game, never()).setState(any());
    }
}