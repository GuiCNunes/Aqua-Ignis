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


public class Maze {
    private int width;
    private int height;

    private Ignis ignis;
    private Aqua aqua;

    private boolean[][] walls;

    private List<Monster> monsters = new ArrayList<>();

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;

        walls = new boolean[height][width];
        generator();

        ignis = new Ignis(11,10);
        aqua = new Aqua(10,10);

        monsters.add(new Ghost(20, 5));
        monsters.add(new Slime(5, 15));
    }

    public Aqua getAqua() {return aqua;}
    public Ignis getIgnis() {return ignis;}

    public List<Monster> getMonsters() {return monsters;}

    public void processKey(KeyStroke key) {
        //devia ser um controller? ainda nao tenho a certeza mas acho que nao pensando no controller como uma ponte
        // ignis setas
        if (key.getKeyType() == KeyType.ArrowUp) movePlayer(ignis, ignis.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) movePlayer(ignis, ignis.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) movePlayer(ignis, ignis.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) movePlayer(ignis, ignis.moveRight());

        // aqua wasd
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'w' || key.getCharacter() == 'W'))
            movePlayer(aqua, aqua.moveUp());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S'))
            movePlayer(aqua, aqua.moveDown());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'a' || key.getCharacter() == 'A'))
            movePlayer(aqua, aqua.moveLeft());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'd' || key.getCharacter() == 'D'))
            movePlayer(aqua, aqua.moveRight());

    }

    private void movePlayer(Player player, Position newPos) {
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
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x == 0 || x == width - 1 || y == 0 || y == height - 1) walls[y][x] = true;
                else if ((x % 4 == 0 && y % 2 == 0) ||( x % 7 == 0 && y % 3 == 0)) walls[y][x] = true;
                else walls[y][x] = false;
            }
        }
        for(int i = 2; i < width - 2; i+=6) walls[2][i] = false;
    }
}