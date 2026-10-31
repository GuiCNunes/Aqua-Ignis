package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
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

import static com.Nunes.aqua_and_ignis.view.Layout.worldX;
import static com.Nunes.aqua_and_ignis.view.Layout.worldY;

public class MazeWorldRenderer {

    private static final double MEMORY_BRIGHTNESS = 0.4;

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

        playerView.draw(maze.getAqua(), gui, Colors.FLOOR);
        playerView.draw(maze.getIgnis(), gui, Colors.FLOOR);
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
        gui.fillRect(worldX(0), worldY(0), maze.getWidth(), maze.getHeight(), Colors.BLACK);
    }

    private void drawTilesAndUpdateMemory(Maze maze, GUI gui, boolean[][] visible) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (!visible[x][y]) continue;

                maze.updateMemory(x, y);
                drawWorldTile(maze, gui, x, y, 1.0);
            }
        }
    }

    private void drawWorldTile(Maze maze, GUI gui, int x, int y, double brightness) {
        Position exit = maze.getExit();

        if (exit != null && x == exit.getX() && y == exit.getY()) {
            drawDoor(maze, gui, x, y, brightness);
        } else if (maze.isWall(x, y)) {
            drawTile(gui, x, y, wallTile(maze, x, y), Colors.WALL_BRICK, Colors.WALL, brightness);
        } else {
            drawTile(gui, x, y, Tile.FLOOR, Colors.FLOOR_DETAIL, Colors.FLOOR, brightness);
        }
    }

    private void drawDoor(Maze maze, GUI gui, int x, int y, double brightness) {
        if (maze.isExitOpen()) {
            drawTile(gui, x, y, Tile.DOOR_OPEN, Colors.DOOR_OPEN, Colors.BLACK, brightness);
        } else {
            drawTile(gui, x, y, Tile.DOOR_CLOSED, Colors.DOOR_CLOSED, Colors.WALL, brightness);
        }
    }

    private void drawTile(GUI gui, int x, int y, Tile tile, Color fg, Color bg, double brightness) {
        gui.drawTile(worldX(x), worldY(y), tile, fg.scaled(brightness), bg.scaled(brightness));
    }

    private Tile wallTile(Maze maze, int x, int y) {
        int neighbours = 0;
        if (isWallOrOutside(maze, x, y - 1)) neighbours += 1;
        if (isWallOrOutside(maze, x + 1, y)) neighbours += 2;
        if (isWallOrOutside(maze, x, y + 1)) neighbours += 4;
        if (isWallOrOutside(maze, x - 1, y)) neighbours += 8;
        return Tile.wall(neighbours);
    }

    private boolean isWallOrOutside(Maze maze, int x, int y) {
        if (x < 0 || y < 0 || x >= maze.getWidth() || y >= maze.getHeight()) return true;
        return maze.isWall(x, y);
    }

    private void drawMemory(Maze maze, GUI gui, boolean[][] visible) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (visible[x][y]) continue;
                if (!isExplored(maze, x, y)) continue;

                drawWorldTile(maze, gui, x, y, MEMORY_BRIGHTNESS);
            }
        }
    }

    private boolean isExplored(Maze maze, int x, int y) {
        if (!maze.isWall(x, y)) return maze.isMemorized(x, y);

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (maze.isMemorized(x + dx, y + dy)) return true;
            }
        }
        return false;
    }

    private void drawStars(Maze maze, GUI gui, boolean[][] visible) {
        for (Star star : maze.getStars()) {
            int x = star.getPosition().getX();
            int y = star.getPosition().getY();
            if (visible[x][y]) starView.draw(gui, star, Colors.FLOOR);
        }
    }

    private void drawKey(Maze maze, GUI gui, boolean[][] visible) {
        Key key = maze.getKey();
        if (key == null) return;

        int x = key.getPosition().getX();
        int y = key.getPosition().getY();
        if (visible[x][y]) keyView.draw(gui, key, Colors.FLOOR);
    }

    private void drawMonsters(Maze maze, GUI gui, boolean[][] visible) {
        for (Monster m : maze.getMonsters()) {
            int x = m.getPosition().getX();
            int y = m.getPosition().getY();
            if (visible[x][y]) monsterView.draw(m, gui, Colors.FLOOR);
        }
    }
}
