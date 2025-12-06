package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Maze;
import model.Position;
import model.entity.Player;

public class MazeController {
    private Maze maze;

    public MazeController(Maze maze) {
        this.maze = maze;
    }

    public void processKey(KeyStroke key) {
        Position newPos = null;
        Player playerToMove = null;

        // ignis setas
        if (key.getKeyType() == KeyType.ArrowUp) {
            playerToMove = maze.getIgnis();
            newPos = maze.getIgnis().moveUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            playerToMove = maze.getIgnis();
            newPos = maze.getIgnis().moveDown();
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            playerToMove = maze.getIgnis();
            newPos = maze.getIgnis().moveLeft();
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            playerToMove = maze.getIgnis();
            newPos = maze.getIgnis().moveRight();
        }

        // aqua wasd
        else if (key.getKeyType() == KeyType.Character) {
            char character = Character.toLowerCase(key.getCharacter());
            if (character == 'w') {
                playerToMove = maze.getAqua();
                newPos = maze.getAqua().moveUp();
            } else if (character == 's') {
                playerToMove = maze.getAqua();
                newPos = maze.getAqua().moveDown();
            } else if (character == 'a') {
                playerToMove = maze.getAqua();
                newPos = maze.getAqua().moveLeft();
            } else if (character == 'd') {
                playerToMove = maze.getAqua();
                newPos = maze.getAqua().moveRight();
            }
        }

        if(playerToMove != null && newPos != null) maze.movePlayer(playerToMove, newPos);

    }

}