package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BorderTest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(92);
        when(gui.getHeight()).thenReturn(32);
    }

    @Test
    void cornersConnectTheEdges() {
        Border.draw(gui);

        verify(gui).drawTile(0, 0, Tile.WALL_6, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(91, 0, Tile.WALL_12, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(0, 31, Tile.WALL_3, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(91, 31, Tile.WALL_9, Colors.WALL_BRICK, Colors.WALL);
    }

    @Test
    void edgesRunBetweenTheCorners() {
        Border.draw(gui);

        verify(gui).drawTile(1, 0, Tile.WALL_10, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(90, 31, Tile.WALL_10, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(0, 1, Tile.WALL_5, Colors.WALL_BRICK, Colors.WALL);
        verify(gui).drawTile(91, 30, Tile.WALL_5, Colors.WALL_BRICK, Colors.WALL);
    }

    @Test
    void onlyTheEdgeIsDrawn() {
        Border.draw(gui);

        verify(gui, times(2 * 92 + 2 * 30)).drawTile(anyInt(), anyInt(), any(), any(), any());
        verify(gui, never()).drawTile(eq(5), eq(5), any(), any(), any());
    }
}
