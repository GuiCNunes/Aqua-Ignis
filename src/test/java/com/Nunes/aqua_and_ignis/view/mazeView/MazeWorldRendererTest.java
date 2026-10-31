package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.*;
import com.Nunes.aqua_and_ignis.view.mazeView.FieldOfView;
import com.Nunes.aqua_and_ignis.view.mazeView.MazeWorldRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.entityView.KeyView;
import com.Nunes.aqua_and_ignis.view.entityView.MonsterView;
import com.Nunes.aqua_and_ignis.view.entityView.PlayerView;
import com.Nunes.aqua_and_ignis.view.entityView.StarView;

import java.util.List;

import static org.mockito.Mockito.*;

class MazeWorldRendererTest {
    private MazeWorldRenderer renderer;
    private Maze maze;
    private GUI gui;
    private FieldOfView fov;
    private Aqua aqua;
    private Ignis ignis;
    private PlayerView playerView;
    private StarView starView;
    private KeyView keyView;
    private MonsterView monsterView;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        maze = mock(Maze.class);
        fov = mock(FieldOfView.class);
        playerView = mock(PlayerView.class);
        starView = mock(StarView.class);
        keyView = mock(KeyView.class);
        monsterView = mock(MonsterView.class);
        aqua = mock(Aqua.class);
        ignis = mock(Ignis.class);

        when(maze.getWidth()).thenReturn(2);
        when(maze.getHeight()).thenReturn(2);
        when(maze.getAqua()).thenReturn(aqua);
        when(maze.getIgnis()).thenReturn(ignis);
        when(aqua.getPosition()).thenReturn(new Position(0, 0));
        when(ignis.getPosition()).thenReturn(new Position(1, 1));

        renderer = new MazeWorldRenderer(fov, playerView, monsterView, starView, keyView);
    }

    @Test
    void orVisibleLogicHitsAllBranches() {
        boolean[][] visA = new boolean[2][2];
        visA[0][0] = true;
        boolean[][] visB = new boolean[2][2];
        visB[1][1] = true;

        when(fov.computeVisible(maze, aqua.getPosition())).thenReturn(visA);
        when(fov.computeVisible(maze, ignis.getPosition())).thenReturn(visB);

        when(maze.isWall(0, 0)).thenReturn(true);
        when(maze.isWall(1, 1)).thenReturn(false);
        when(maze.getExit()).thenReturn(null);

        renderer.render(maze, gui);

        verify(gui).fillRect(0, 0, 1, 1, Colors.WALL);
        verify(gui).fillRect(1, 1, 1, 1, Colors.FLOOR);
        verify(maze).updateMemory(0, 0);
        verify(maze).updateMemory(1, 1);
    }

    @Test
    void drawTilesWithExitOpen() {
        boolean[][] vis = {{true, true}, {true, true}};
        when(fov.computeVisible(any(), any())).thenReturn(vis);
        when(maze.getExit()).thenReturn(new Position(1, 0));
        when(maze.isExitOpen()).thenReturn(true);

        renderer.render(maze, gui);

        verify(gui).fillRect(1, 0, 1, 1, Colors.WHITE);
    }

    @Test
    void drawMemoryAndSkipVisible() {
        boolean[][] vis = new boolean[2][2];
        vis[0][0] = true;
        when(fov.computeVisible(any(), any())).thenReturn(vis);

        when(maze.isMemorized(0, 0)).thenReturn(true);
        when(maze.isMemorized(1, 1)).thenReturn(true);
        when(maze.isMemorized(0, 1)).thenReturn(false);

        renderer.render(maze, gui);

        verify(gui, never()).fillRect(0, 0, 1, 1, Colors.SHADOW);
        verify(gui).fillRect(1, 1, 1, 1, Colors.SHADOW);
    }

    @Test
    void drawEntitiesVisibilityBranchTrue() {
        boolean[][] vis = new boolean[2][2];
        vis[0][0] = true;
        when(fov.computeVisible(any(), any())).thenReturn(vis);

        Star s = mock(Star.class);
        when(s.getPosition()).thenReturn(new Position(0, 0));
        when(maze.getStars()).thenReturn(List.of(s));

        Key k = mock(Key.class);
        when(k.getPosition()).thenReturn(new Position(0, 0));
        when(maze.getKey()).thenReturn(k);

        Monster m = mock(Monster.class);
        when(m.getPosition()).thenReturn(new Position(0, 0));
        when(maze.getMonsters()).thenReturn(List.of(m));

        renderer.render(maze, gui);

        verify(starView).draw(gui, s);
        verify(keyView).draw(gui, k);
        verify(monsterView).draw(m, gui);
    }

    @Test
    void drawEntitiesVisibilityBranchFalse() {
        boolean[][] vis = new boolean[2][2];
        vis[0][0] = true;
        vis[1][1] = false;
        when(fov.computeVisible(any(), any())).thenReturn(vis);

        Star s = mock(Star.class);
        when(s.getPosition()).thenReturn(new Position(1, 1));
        when(maze.getStars()).thenReturn(List.of(s));

        Key k = mock(Key.class);
        when(k.getPosition()).thenReturn(new Position(1, 1));
        when(maze.getKey()).thenReturn(k);

        Monster m = mock(Monster.class);
        when(m.getPosition()).thenReturn(new Position(1, 1));
        when(maze.getMonsters()).thenReturn(List.of(m));

        renderer.render(maze, gui);

        verify(starView, never()).draw(any(), any());
        verify(keyView, never()).draw(any(), any());
        verify(monsterView, never()).draw(any(), any());
    }

    @Test
    void drawKeyNullAndExitNull() {
        when(fov.computeVisible(any(), any())).thenReturn(new boolean[2][2]);
        when(maze.getKey()).thenReturn(null);
        when(maze.getExit()).thenReturn(null);

        renderer.render(maze, gui);

        verify(keyView, never()).draw(any(), any());
    }

    @Test
    void exitClosedOrMismatch() {
        boolean[][] vis = {{true, true}, {true, true}};
        when(fov.computeVisible(any(), any())).thenReturn(vis);

        when(maze.getExit()).thenReturn(new Position(0, 0));
        when(maze.isExitOpen()).thenReturn(false);
        when(maze.isWall(0, 0)).thenReturn(false);

        renderer.render(maze, gui);
        verify(gui).fillRect(0, 0, 1, 1, Colors.FLOOR);
    }
}