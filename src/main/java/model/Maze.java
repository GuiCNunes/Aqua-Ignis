package model;

import model.entity.Ignis;
import model.entity.Player;
import model.entity.Aqua;
import model.entity.Slime;
import model.entity.Monster;
import model.entity.Ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Maze {
    private int width;
    private int height;

    private Ignis ignis;
    private Aqua aqua;

    private boolean[][] walls;

    private Position exit;

    private List<Monster> monsters = new ArrayList<>();

    private enum Cardinal {north, south, east, west}

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        walls = new boolean[height][width];

        int centerX = width / 2;
        int centerY = height / 2;

        ignis = new Ignis(centerX - 1,centerY);
        aqua = new Aqua(centerX + 1,centerY);

        exit = randomExit();

        generator();

        monsters.add(new Ghost(20, 5));
        monsters.add(new Slime(5, 15));
    }

    public Aqua getAqua() {return aqua;}
    public Ignis getIgnis() {return ignis;}

    public List<Monster> getMonsters() {return monsters;}


    public void movePlayer(Player player, Position newPos) {
        if (canMove(newPos) && !walls[newPos.getY()][newPos.getX()]) {
            player.setPosition(newPos);
        }
    }

    private boolean canMove(Position p) {
        return p.getX() >= 0 && p.getX() < width &&
                p.getY() >= 0 && p.getY() < height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public boolean isWall(int x, int y) {
        return walls[y][x];
    }

    private Position randomExit(){
        Random random = new Random();
        Cardinal cardinal = Cardinal.values()[random.nextInt(Cardinal.values().length)];
        int randomCord;

        switch (cardinal) {
            case north:
                randomCord = random.nextInt((width - 2)/2) * 2 + 1;
                return new Position(randomCord, 0);
            case south:
                randomCord = random.nextInt((width - 2)/2) * 2 + 1;
                return new Position(randomCord, height - 1);
            case east:
                randomCord = random.nextInt((height - 2)/2) * 2 + 1;
                return new Position(width - 1, randomCord);
            case west:
                randomCord = random.nextInt((height - 2)/2) * 2 + 1;
                return new Position(0, randomCord);
        }
        return new Position(width/2, height -1);

    }

    private void generator(){

        Random random = new Random();

        int xFactor_1 = random.nextInt(4) + 2;
        int yFactor_1 = random.nextInt(4) + 2;

        int xFactor_2 = random.nextInt(6) + 3;
        int yFactor_2 = random.nextInt(6) + 3;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x == 0 || x == width - 1 || y == 0 || y == height - 1) walls[y][x] = true;
                else if ((x % xFactor_1 == 0 && y % yFactor_1 == 0) ||( x % xFactor_2 == 0 && y % yFactor_2 == 0)) walls[y][x] = true;
                else walls[y][x] = false;
            }
        }
        for(int i = 2; i < width - 2; i+=6) walls[2][i] = false;
        walls[exit.getY()][exit.getX()] = false;

        int roomSize = random.nextInt(4) + 4;
        int half = roomSize / 2;


        int centerX = width/2;
        int startX = centerX - half;
        int endX = centerX + half;

        int centerY = height/2;
        int startY = centerY - half;
        int endY = centerY + half;

        for (int y = startY + 1; y < endY; y++ ) {
            for (int x = startX + 1; x < endX; x++ ) {
                walls[y][x] = false;
            }
        }

        for (int x = startX; x <= endX; x++ ) {
            walls[startY][x] = true;
            walls[endY][x] = true;
        }

        for(int y = startY; y <= endY; y++ ) {
            walls[y][startX] = true;
            walls[y][endX] = true;
        }

        walls[startY][centerX] = false; //norte, sul etc...
        walls[endY][centerX] = false;
        walls[centerY][startX] = false;
        walls[centerY][endX] = false;

    }

    public Position getExit() {return exit;}

    public boolean checkWin(Position position){
        if (position.getX() == exit.getX() && position.getY() == exit.getY()) return true;
        return false;
    }

    public boolean checkLoss(){
        Position aquaPos = aqua.getPosition();
        Position ignisPos = ignis.getPosition();

        for(Monster monster : monsters){
            Position monsterPos = monster.getPosition();

            if(monsterPos.getX() == aquaPos.getX() && monsterPos.getY() == aquaPos.getY()) return true;
            if(monsterPos.getX() == ignisPos.getX() && monsterPos.getY() == ignisPos.getY()) return true;
        }
        return false;
    }

}