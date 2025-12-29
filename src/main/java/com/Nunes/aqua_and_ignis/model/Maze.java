package com.Nunes.aqua_and_ignis.model;

import com.Nunes.aqua_and_ignis.model.entity.*;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private int width;
    private int height;
    private Ignis ignis;
    private Aqua aqua;
    private Key key;
    private boolean exitOpen;
    private boolean[][] walls;
    private boolean[][] memory;
    private Position exit;
    private List<Monster> monsters = new ArrayList<>();
    private List<Star> stars = new ArrayList<>();
    private int collectedStars;
    private long startTime;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new boolean[height][width];
        this.memory = new boolean[height][width];
        this.startTime = System.currentTimeMillis();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setWall(int x, int y, boolean wall) { this.walls[y][x] = wall; }
    public boolean isWall(int x, int y) { return walls[y][x]; }

    public Ignis getIgnis() { return ignis; }
    public void setIgnis(Ignis ignis) { this.ignis = ignis; }
    public Aqua getAqua() { return aqua; }
    public void setAqua(Aqua aqua) { this.aqua = aqua; }

    public Position getExit() { return exit; }
    public void setExit(Position exit) { this.exit = exit; }
    public boolean isExitOpen() { return exitOpen; }
    public void setExitOpen(boolean exitOpen) { this.exitOpen = exitOpen; }

    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }

    public List<Monster> getMonsters() { return monsters; }
    public void addMonster(Monster monster) { this.monsters.add(monster); }

    public List<Star> getStars() { return stars; }
    public int getCollectedStars() { return collectedStars; }
    public void incrementStars() { this.collectedStars++; }

    public void updateMemory(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height && !isWall(x, y)) {
            memory[y][x] = true;
        }
    }

    public boolean isMemorized(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) return memory[y][x];
        return false;
    }

    public long getTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}