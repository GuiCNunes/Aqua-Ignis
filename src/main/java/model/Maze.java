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

    private enum Cardinal {east, west}

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
            case east:
                randomCord = random.nextInt((height - 10)/2) * 2 + 1;
                return new Position(width - 1, randomCord);
            case west:
                randomCord = random.nextInt((height - 10)/2) * 2 + 1;
                return new Position(0, randomCord);
        }
        return new Position(width/2, height -1);

    }

    private void generator(){

        Random random = new Random();

        for(int y  = 0; y < height; y++){
            for(int x  = 0; x < width; x++){
                walls[y][x] = true;
            }
        }

        walls[exit.getY()][exit.getX()] = false;

        int pathX = width/2;
        int pathY = height/2;
        if(pathX%2 == 0) pathX --;
        if(pathY%2 == 0) pathY --;

        path(pathX, pathY);

        int eX = exit.getX();
        int eY = exit.getY();

        switch(getExitCardinal()) {
            case east:
                eX = width - 3;
                eY = (eY % 2 == 0) ? eY : eY - 1;
                break;
            case west:
                eX = 1;
                eY = (eY % 2 == 0) ? eY : eY - 1;
                break;
        }

        if(eX >= 1 && eX < width - 2 && eY >= 1 && eY < height - 2){
            walls[eY][eX] = false;
            walls[eY+1][eX] = false;
            walls[eY][eX+1] = false;
            walls[eY+1][eX+1] = false;
        }


        int roomSize = random.nextInt(4) + 4;
        int half = roomSize / 2;

        int centerX = width/2;
        int centerY = height/2;

        int startX = centerX - half;
        int endX = centerX + half;

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

        walls[startY][centerX] = false;
        walls[endY][centerX] = false;
        walls[centerY][startX] = false;
        walls[centerY][endX] = false;

    }

    private void path(int x, int y){

        if(x <= 0 || x >= width - 2 || y <= 0 || y >= height - 2) return;

        walls[y][x] = false;
        walls[y+1][x] = false;
        walls[y][x+1] = false;
        walls[y+1][x+1] = false;

        int[]dx = {0,0,4,-4};
        int[]dy = {4,-4,0,0};

        int[] direction = {0,1,2,3};

        Random random = new Random();

        for(int i = direction.length - 1; i >= 0; i--){
            int index = random.nextInt(i+1);
            int temp = direction[index];
            direction[index] = direction[i];
            direction[i] = temp;
        }

        for(int i : direction){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx > 0 && nx < width - 2 && ny > 0 && ny < height - 2 && walls[ny][nx]){

                int directionX = dx[i] / 4;
                int directionY = dy[i] / 4;

                walls[y + directionY][x + directionX] = false;
                walls[y + directionY*2][x + directionX*2] = false;
                walls[y + directionY*3][x + directionX*3] = false;

                path(nx, ny);
            }
        }
    }



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

    private Cardinal getExitCardinal(){
        int eX = exit.getX();

        if(eX == width - 1) return Cardinal.east;
        if(eX == 0) return Cardinal.west;

        return Cardinal.east;
    }
}