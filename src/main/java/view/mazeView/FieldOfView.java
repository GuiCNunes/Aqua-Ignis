package view.mazeView;

import model.Maze;
import model.Position;

public class FieldOfView {
    private final int radius;

    public FieldOfView(int radius) {
        this.radius = radius;
    }

    public boolean[][] computeVisible(Maze maze, Position from) {
        int w = maze.getWidth();
        int h = maze.getHeight();
        boolean[][] visible = new boolean[w][h];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                visible[x][y] = isVisible(maze, from, x, y);
            }
        }
        return visible;
    }

    public boolean isVisible(Maze maze, Position from, int x, int y) {
        int dx = from.getX() - x;
        int dy = from.getY() - y;

        if (dx * dx + dy * dy > radius * radius) return false;

        int x0 = from.getX();
        int y0 = from.getY();

        int absDx = Math.abs(x - x0);
        int absDy = Math.abs(y - y0);

        int stepX = (x0 < x) ? 1 : -1;
        int stepY = (y0 < y) ? 1 : -1;

        int dif = absDx - absDy;
        int currentX = x0;
        int currentY = y0;

        while (currentX != x || currentY != y) {
            if (maze.isWall(currentX, currentY) && (currentX != x0 || currentY != y0)) {
                return false;
            }

            int dif2 = 2 * dif;

            if (dif2 > -absDy) {
                dif -= absDy;
                currentX += stepX;
            }

            if (dif2 < absDx) {
                dif += absDx;
                currentY += stepY;
            }
        }
        return true;
    }
}
