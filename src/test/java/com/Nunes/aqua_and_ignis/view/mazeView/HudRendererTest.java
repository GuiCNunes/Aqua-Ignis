package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.view.mazeView.HudRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static org.mockito.Mockito.*;

class HudRendererTest {
    private HudRenderer renderer;
    private Maze maze;
    private GUI gui;

    @BeforeEach
    void setUp() {
        renderer = new HudRenderer();
        maze = mock(Maze.class);
        gui = mock(GUI.class);
    }

    @Test
    void renderStarsFull() {
        when(maze.getCollectedStars()).thenReturn(3);
        when(maze.isExitOpen()).thenReturn(true);
        when(maze.getTime()).thenReturn(125L);

        renderer.render(maze, gui);

        verify(gui).drawText(3, 1, "Stars: ***", Colors.STAR, Colors.BG_UI);
        verify(gui).drawText(20, 1, "Exit : OPEN", Colors.SLIME, Colors.BG_UI);
        verify(gui).drawText(40, 1, "Time: 02:05", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void renderStarsEmptyAndExitClosed() {
        when(maze.getCollectedStars()).thenReturn(0);
        when(maze.isExitOpen()).thenReturn(false);
        when(maze.getTime()).thenReturn(59L);

        renderer.render(maze, gui);

        verify(gui).drawText(3, 1, "Stars: ---", Colors.STAR, Colors.BG_UI);
        verify(gui).drawText(20, 1, "Exit : CLOSED", Colors.KEY, Colors.BG_UI);
        verify(gui).drawText(40, 1, "Time: 00:59", Colors.WHITE, Colors.BG_UI);
    }

    @Test
    void renderDrawsHudBackground() {
        when(gui.getWidth()).thenReturn(92);

        renderer.render(maze, gui);

        verify(gui).fillRect(0, 0, 92, 2, Colors.BG_UI);
    }

    @Test
    void renderStarsPartial() {
        when(maze.getCollectedStars()).thenReturn(1);
        renderer.render(maze, gui);
        verify(gui).drawText(3, 1, "Stars: *--", Colors.STAR, Colors.BG_UI);
    }
}