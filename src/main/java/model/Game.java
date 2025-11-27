package model;

import controller.MazeController;
import view.MazeView;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Maze maze;
    private MazeView mazeView;
    private Screen screen;
    private MazeController mazeController;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(60, 30);

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Terminal terminal = terminalFactory.createTerminal();

            this.screen = new TerminalScreen(terminal);

            TerminalSize size = terminal.getTerminalSize();

            this.maze = new Maze(size.getColumns(), size.getRows());
            this.mazeView = new MazeView();
            this.mazeController = new MazeController(maze);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {

        screen.clear();

        mazeView.draw(maze, screen.newTextGraphics());

        screen.refresh();

    }

    private void processKey(KeyStroke key) {
        mazeController.processKey(key);
    }



    public void run() throws IOException {

        draw();

        while (true) {
            KeyStroke key;
            key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.stopScreen();
                break;
            }

            if(key.getKeyType() == KeyType.EOF){break;}

            processKey(key);
            draw();
        }
    }

}