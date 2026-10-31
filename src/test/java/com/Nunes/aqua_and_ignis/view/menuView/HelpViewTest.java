package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.menuView.HelpView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HelpViewTest {
    private HelpView view;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        gui = mock(GUI.class);
        view = new HelpView(menu);
    }

    @Test
    void testDrawTitles() throws IOException {
        view.draw(gui);

        verify(gui).drawText(5, 5, "OBJECTIVE:", Colors.TITLE, Colors.BG_UI);
        verify(gui).drawText(5, 11, "CONTROLS:", Colors.TITLE, Colors.BG_UI);
    }

    @Test
    void testDrawInstructions() throws IOException {
        view.draw(gui);

        verify(gui).drawText(5, 7, "Work together to collect 3 STARS and find the KEY.", Colors.WHITE, Colors.BG_UI);
        verify(gui).drawText(5, 8, "Both players must reach the EXIT once it is open!", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void testDrawControls() throws IOException {
        view.draw(gui);

        verify(gui).drawText(5, 13, "AQUA:", Colors.AQUA, Colors.BG_UI);
        verify(gui).drawText(20, 13, "W A S D to Move", Colors.WHITE, Colors.BG_UI);

        verify(gui).drawText(5, 14, "IGNIS:", Colors.IGNIS, Colors.BG_UI);
        verify(gui).drawText(20, 14, "ARROW KEYS to Move", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void testDrawFooter() throws IOException {
        view.draw(gui);

        verify(gui).drawText(5, 18, "Press ENTER to return to Main Menu", Colors.STAR, Colors.BG_UI);
    }

    @Test
    void testDrawSprites() throws IOException {
        view.draw(gui);

        verify(gui).drawTile(2, 7, Tile.STAR, Colors.STAR, Colors.BG_UI);
        verify(gui).drawTile(3, 7, Tile.KEY, Colors.KEY, Colors.BG_UI);
        verify(gui).drawTile(3, 8, Tile.DOOR_OPEN, Colors.DOOR_OPEN, Colors.BG_UI);
        verify(gui).drawTile(3, 13, Tile.AQUA, Colors.AQUA, Colors.BG_UI);
        verify(gui).drawTile(3, 14, Tile.IGNIS, Colors.IGNIS, Colors.BG_UI);
    }

    @Test
    void testDrawBorder() throws IOException {
        when(gui.getWidth()).thenReturn(92);
        when(gui.getHeight()).thenReturn(32);

        view.draw(gui);

        verify(gui).drawTile(0, 0, Tile.WALL_6, Colors.WALL_BRICK, Colors.WALL);
    }
}
