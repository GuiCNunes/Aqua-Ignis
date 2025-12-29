package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Key;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.entityView.KeyView;
import com.Nunes.aqua_and_ignis.view.entityView.MonsterView;
import com.Nunes.aqua_and_ignis.view.entityView.PlayerView;
import com.Nunes.aqua_and_ignis.view.entityView.StarView;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;

public class MazeWorldRenderer {

    private final FieldOfView fov;

    private final PlayerView playerView;
    private final MonsterView monsterView;
    private final StarView starView;
    private final KeyView keyView;

    public MazeWorldRenderer(FieldOfView fov, PlayerView playerView, MonsterView monsterView,
                             StarView starView, KeyView keyView) {
        this.fov = fov;
        this.playerView = playerView;
        this.monsterView = monsterView;
        this.starView = starView;
        this.keyView = keyView;
    }

    private int sx(int x) { return x * SCALE; }
    private int sy(int y) { return y * SCALE; }

    public void render(Maze maze, GUI gui) {
        Position aquaPos = maze.getAqua().getPosition();
        Position ignisPos = maze.getIgnis().getPosition();

        boolean[][] visibleAqua = fov.computeVisible(maze, aquaPos);
        boolean[][] visibleIgnis = fov.computeVisible(maze, ignisPos);

        boolean[][] visible = orVisible(maze, visibleAqua, visibleIgnis);

        drawDarkness(maze, gui);
        drawTilesAndUpdateMemory(maze, gui, visible);
        drawMemory(maze, gui, visible);

        drawStars(maze, gui, visible);
        drawKey(maze, gui, visible);
        drawMonsters(maze, gui, visible);

        playerView.draw(maze.getAqua(), gui);
        playerView.draw(maze.getIgnis(), gui);
    }

    private boolean[][] orVisible(Maze maze, boolean[][] a, boolean[][] b) {
        int w = maze.getWidth();
        int h = maze.getHeight();
        boolean[][] v = new boolean[w][h];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                v[x][y] = a[x][y] || b[x][y];
            }
        }
        return v;
    }

    private void drawDarkness(Maze maze, GUI gui) {
        gui.fillRect(0, 0, maze.getWidth() * SCALE, maze.getHeight() * SCALE, Colors.BLACK);
    }

    private void drawTilesAndUpdateMemory(Maze maze, GUI gui, boolean[][] visible) {
        Position exit = maze.getExit();

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (!visible[x][y]) continue;

                maze.updateMemory(x, y);

                var color = maze.isWall(x, y) ? Colors.WALL : Colors.FLOOR;

                if (exit != null && x == exit.getX() && y == exit.getY() && maze.isExitOpen()) {
                    color = Colors.WHITE; // ajusta para uma cor própria se quiseres
                }

                gui.fillRect(sx(x), sy(y), SCALE, SCALE, color);
            }
        }
    }

    private void drawMemory(Maze maze, GUI gui, boolean[][] visible) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (!maze.isMemorized(x, y)) continue;
                if (visible[x][y]) continue;

                gui.fillRect(sx(x), sy(y), SCALE, SCALE, Colors.SHADOW);
            }
        }
    }

    private void drawStars(Maze maze, GUI gui, boolean[][] visible) {
        for (Star star : maze.getStars()) {
            int x = star.getPosition().getX();
            int y = star.getPosition().getY();
            if (visible[x][y]) starView.draw(gui, star);
        }
    }

    private void drawKey(Maze maze, GUI gui, boolean[][] visible) {
        Key key = maze.getKey();
        if (key == null) return;

        int x = key.getPosition().getX();
        int y = key.getPosition().getY();
        if (visible[x][y]) keyView.draw(gui, key);
    }

    private void drawMonsters(Maze maze, GUI gui, boolean[][] visible) {
        for (Monster m : maze.getMonsters()) {
            int x = m.getPosition().getX();
            int y = m.getPosition().getY();
            if (visible[x][y]) monsterView.draw(m, gui);
        }
    }
}
