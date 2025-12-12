package model;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        walls = new boolean[height][width];

        ignis = new Ignis(11,10);
        aqua = new Aqua(10,10);

        exit = new Position(width/2,height-1);

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