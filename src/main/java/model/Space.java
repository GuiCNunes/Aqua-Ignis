package model;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;


public class Space {
    private int width;
    private int height;

    private Ignis ignis;
    private Aqua aqua;

    public Space(int width, int height) {
        this.width = width;
        this.height = height;

        ignis = new Ignis(11,10);
        aqua = new Aqua(10,10);
    }

    public Aqua getAqua() {return aqua;}
    public Ignis getIgnis() {return ignis;}

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
        if (canMove(newPos)) {
            player.setPosition(newPos);
        }
    }

    private boolean canMove(Position p) {
        return p.getX() >= 0 && p.getX() < width &&
                p.getY() >= 0 && p.getY() < height;
    }
}
