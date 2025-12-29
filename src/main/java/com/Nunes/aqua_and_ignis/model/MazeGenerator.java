package com.Nunes.aqua_and_ignis.model;

import com.Nunes.aqua_and_ignis.model.entity.Aqua;
import com.Nunes.aqua_and_ignis.model.entity.Ignis;
import com.Nunes.aqua_and_ignis.model.entity.Key;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import java.util.*;

public class MazeGenerator {
    private final Random random = new Random();
    private enum Cardinal { EAST, WEST }

    public void generate(Maze maze) {
        int w = maze.getWidth();
        int h = maze.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                maze.setWall(x, y, true);
            }
        }

        Position exitPos = randomExit(w, h);
        maze.setExit(exitPos);
        maze.setWall(exitPos.getX(), exitPos.getY(), false);

        int startX = w / 2;
        int startY = h / 2;
        if (startX % 2 == 0) startX--;
        if (startY % 2 == 0) startY--;

        path(maze, startX, startY);

        carveExitRoom(maze);
        createCenterRoom(maze);

        maze.setIgnis(new Ignis(w / 2 - 1, h / 2));
        maze.setAqua(new Aqua(w / 2 + 1, h / 2));

        generateItems(maze);
    }

    private Position randomExit(int w, int h) {
        Cardinal cardinal = Cardinal.values()[random.nextInt(2)];

        int offsetTop = 4;
        int offsetBottom = 4;
        int availableHeight = h - offsetTop - offsetBottom;

        int randomCord = (random.nextInt(availableHeight / 2) * 2) + offsetTop;

        if (cardinal == Cardinal.EAST) return new Position(w - 1, randomCord);
        return new Position(0, randomCord);
    }

    private void path(Maze maze, int x, int y) {
        maze.setWall(x, y, false);
        maze.setWall(x + 1, y, false);
        maze.setWall(x, y + 1, false);
        maze.setWall(x + 1, y + 1, false);

        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions);

        int[] dx = {0, 0, 4, -4};
        int[] dy = {4, -4, 0, 0};

        for (int i : directions) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > 0 && nx < maze.getWidth() - 2 && ny > 0 && ny < maze.getHeight() - 2 && maze.isWall(nx, ny)) {
                int midX = dx[i] / 4;
                int midY = dy[i] / 4;

                for (int step = 1; step <= 3; step++) {
                    maze.setWall(x + midX * step, y + midY * step, false);
                }
                path(maze, nx, ny);
            }
        }
    }

    private void carveExitRoom(Maze maze) {
        int ex = maze.getExit().getX();
        int ey = maze.getExit().getY();
        int w = maze.getWidth();
        int h = maze.getHeight();

        Cardinal direction = (ex == w - 1) ? Cardinal.EAST : Cardinal.WEST;

        int roomX = (direction == Cardinal.EAST) ? w - 3 : 1;
        int roomY = (ey % 2 == 0) ? ey : ey - 1;

        if (roomX >= 1 && roomX < w - 2 && roomY >= 1 && roomY < h - 2) {
            maze.setWall(roomX, roomY, false);
            maze.setWall(roomX + 1, roomY, false);
            maze.setWall(roomX, roomY + 1, false);
            maze.setWall(roomX + 1, roomY + 1, false);
        }
    }

    private void createCenterRoom(Maze maze) {
        int centerX = maze.getWidth() / 2;
        int centerY = maze.getHeight() / 2;
        int roomSize = random.nextInt(4) + 4;
        int half = roomSize / 2;

        for (int y = centerY - half + 1; y < centerY + half; y++) {
            for (int x = centerX - half + 1; x < centerX + half; x++) {
                maze.setWall(x, y, false);
            }
        }

        maze.setWall(centerX, centerY - half, false);
        maze.setWall(centerX, centerY + half, false);
        maze.setWall(centerX - half, centerY, false);
        maze.setWall(centerX + half, centerY, false);
    }

    private void generateItems(Maze maze) {
        for (int i = 0; i < 3; i++) {
            Position p = getFreePosition(maze);
            maze.getStars().add(new Star(p.getX(), p.getY()));
        }
        Position keyPos = getFreePosition(maze);
        maze.setKey(new Key(keyPos.getX(), keyPos.getY()));
    }

    public Position getFreePosition(Maze maze) {
        while (true) {
            int x = random.nextInt(maze.getWidth());
            int y = random.nextInt(maze.getHeight());
            if (!maze.isWall(x, y)) {
                Position p = new Position(x, y);
                if (maze.getIgnis() != null && p.equals(maze.getIgnis().getPosition())) continue;
                if (maze.getAqua() != null && p.equals(maze.getAqua().getPosition())) continue;
                return p;
            }
        }
    }
}