package view.menuView;

import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuViewTest {
    private MenuView view;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        gui = mock(GUI.class);
        view = new MenuView(menu);
    }

    @Test
    void testDrawTitle() throws IOException {
        when(menu.getNumberEntries()).thenReturn(0);

        view.draw(gui);

        verify(gui).drawString(5, 5, "Aqua", Colors.AQUA, null);
        verify(gui).drawString(10, 5, "&", Colors.GREEN, null);
        verify(gui).drawString(12, 5, "Ignis", Colors.IGNIS, null);
    }

    @Test
    void testDrawEntries() throws IOException {
        when(menu.getNumberEntries()).thenReturn(2);
        when(menu.getEntry(0)).thenReturn("Start");
        when(menu.getEntry(1)).thenReturn("Exit");

        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);

        view.draw(gui);

        verify(gui).drawString(5, 7, "Start", Colors.GOLD, null);
        verify(gui).drawString(5, 8, "Exit", Colors.WHITE, null);
    }

    @Test
    void testDrawEmpty() throws IOException {
        when(menu.getNumberEntries()).thenReturn(0);

        view.draw(gui);

        verify(gui, times(3)).drawString(anyInt(), anyInt(), anyString(), any(), any());
    }

    @Test
    void testDrawBoundaryAndIteration() throws IOException {
        when(menu.getNumberEntries()).thenReturn(1);
        when(menu.getEntry(0)).thenReturn("Test");
        when(menu.isSelected(0)).thenReturn(false);

        view.draw(gui);

        verify(gui).drawString(5, 7, "Test", Colors.WHITE, null);

        reset(gui);
        when(menu.getNumberEntries()).thenReturn(0);

        view.draw(gui);

        verify(gui, never()).drawString(eq(5), eq(7), anyString(), any(), any());
    }
}