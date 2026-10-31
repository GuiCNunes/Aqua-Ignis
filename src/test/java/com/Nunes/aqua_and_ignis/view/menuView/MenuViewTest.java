package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.menuView.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

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

        verify(gui).drawString(5, 5, "Aqua", Colors.AQUA, Colors.BG_UI);
        verify(gui).drawString(10, 5, "&", Colors.GREEN, Colors.BG_UI);
        verify(gui).drawString(12, 5, "Ignis", Colors.IGNIS, Colors.BG_UI);
    }

    @Test
    void testDrawEntries() throws IOException {
        when(menu.getNumberEntries()).thenReturn(2);
        when(menu.getEntry(0)).thenReturn("Start");
        when(menu.getEntry(1)).thenReturn("Exit");

        when(menu.isSelected(0)).thenReturn(true);
        when(menu.isSelected(1)).thenReturn(false);

        view.draw(gui);

        verify(gui).drawString(5, 7, "Start", Colors.GOLD, Colors.BG_UI);
        verify(gui).drawString(5, 8, "Exit", Colors.WHITE, Colors.BG_UI);
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

        verify(gui).drawString(5, 7, "Test", Colors.WHITE, Colors.BG_UI);

        reset(gui);
        when(menu.getNumberEntries()).thenReturn(0);

        view.draw(gui);

        verify(gui, never()).drawString(eq(5), eq(7), anyString(), any(), any());
    }

    @Test
    void testDrawSprites() throws IOException {
        view.draw(gui);

        verify(gui).drawTile(3, 5, Tile.AQUA, Colors.AQUA, Colors.BG_UI);
        verify(gui).drawTile(18, 5, Tile.IGNIS, Colors.IGNIS, Colors.BG_UI);
    }

    @Test
    void testDrawBorder() throws IOException {
        when(gui.getWidth()).thenReturn(92);
        when(gui.getHeight()).thenReturn(32);

        view.draw(gui);

        verify(gui).drawTile(0, 0, Tile.WALL_6, Colors.WALL_BRICK, Colors.WALL);
    }
}
