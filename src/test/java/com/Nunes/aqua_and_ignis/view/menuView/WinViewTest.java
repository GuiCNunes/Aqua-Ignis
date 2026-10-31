package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.menuView.WinView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class WinViewTest {
    private WinView view;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        gui = mock(GUI.class);
        view = new WinView(menu, 3, 120L);
    }

    @Test
    void testDrawWinMessages() throws IOException {
        view.draw(gui);

        verify(gui).drawText(10, 10, "CONGRATULATIONS!", Colors.GREEN, Colors.BG_UI);
        verify(gui).drawText(10, 12, "YOU ESCAPED THE MAZE", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void testDrawStats() throws IOException {
        view.draw(gui);

        verify(gui).drawText(10, 13, "Stars Collected: 3/3", Colors.YELLOW, Colors.BG_UI);
        verify(gui).drawText(10, 14, "Total Time: 120s", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void testDrawFooter() throws IOException {
        view.draw(gui);

        verify(gui).drawText(10, 16, "Press enter to return to main menu", Colors.YELLOW, Colors.BG_UI);
    }
}