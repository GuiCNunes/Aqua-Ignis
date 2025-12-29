package view.menuView;

import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class LoseViewTest {
    private LoseView view;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        gui = mock(GUI.class);
        view = new LoseView(menu);
    }

    @Test
    void testDrawLoseMessages() throws IOException {
        view.draw(gui);

        verify(gui).drawText(10, 10, "GAME OVER!", Colors.KEY, Colors.BG_UI);
        verify(gui).drawText(10, 12, "YOU DIED", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void testDrawFooter() throws IOException {
        view.draw(gui);

        verify(gui).drawText(10, 13, "Press enter to go back to main menu", Colors.YELLOW, Colors.BG_UI);
    }
}