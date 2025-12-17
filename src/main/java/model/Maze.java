package model;

import model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Maze {
    private int width;
    private int height;

    private Ignis ignis;
    private Aqua aqua;

    private boolean[][] walls;

    private boolean[][] memory;

    private Position exit;

    private List<Monster> monsters = new ArrayList<>();
    private List<Star> stars = new ArrayList<>();

    private int collectedStars;

    private enum Cardinal {east, west}

    private Random random = new Random();

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        walls = new boolean[height][width];
        memory = new boolean[height][width];

        int centerX = width / 2;
        int centerY = height / 2;

        stars = new ArrayList<>(5);
        collectedStars = 0;

        ignis = new Ignis(centerX - 1,centerY);
        aqua = new Aqua(centerX + 1,centerY);

        exit = randomExit();

        generator();
        generateStars();
    }

    public Aqua getAqua() {return aqua;}
    public Ignis getIgnis() {return ignis;}

    public List<Monster> getMonsters() {return monsters;}

    public Position getFreePosition(){
        int x,y;

        while(true){
            x = random.nextInt(width);
            y = random.nextInt(height);

            if (walls[y][x]) continue;

            Position ignisPosition = ignis.getPosition();
            Position aquaPosition = aqua.getPosition();

            if((ignisPosition.getX() == x && ignisPosition.getY() == y)
                    || (aquaPosition.getX() == x && aquaPosition.getY() == y)) continue;

            boolean free = true;

            for(Monster monster : monsters) {
                Position monsterPosition = monster.getPosition();
                if((monsterPosition.getX() == x && monsterPosition.getY() == y)){
                    free = false;
                    break;
                }
            }

            if(!free) continue;
            else if (free) {
                return new Position(x,y);
            }
        }
    }

    public void moveEntity(Entity entity, Position newPos, boolean phase) {

        if(!canMove(newPos)) return;

        if(phase) {
            entity.setPosition(newPos);
        }

        else if (!walls[newPos.getY()][newPos.getX()]) {
            entity.setPosition(newPos);
        }

        if(entity instanceof Player){
            collectStars(entity.getPosition());
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

    public void updateMemory(int x, int y) {
        if(x >= 0 && x < width && y >= 0 && y < height) {
            if(!isWall(x, y)) {
                memory[y][x] = true;
            }
        }
    }

    public boolean isMemorized(int x, int y){
        if(x >= 0 && x < width && y >= 0 && y < height){
            return memory[y][x];
        }
        return false;
    }

    private Position randomExit(){
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

    private Cardinal getExitCardinal(){
        int eX = exit.getX();

        if(eX == width - 1) return Cardinal.east;
        if(eX == 0) return Cardinal.west;

        return Cardinal.east;
    }

    public Position getExit(){
        return exit;
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    private List<Star> getStars(){
        return stars;
    }

    public int getCollectedStars(){
        return collectedStars;
    }

    private void generateStars(){
        for(int i = 0; i < stars.size(); i++){
            Position starPosition = getFreePosition();
            stars.add(new Star(starPosition.getX(), starPosition.getY()));
        }
    }

    private void collectStars(Position position){
        for (Star star : stars){
            if(star.getPosition().getX() == position.getX() && star.getPosition().getY() == position.getY()){
                collectedStars++;
                stars.remove(star);
            }
        }
    }
}