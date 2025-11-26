import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Player aqua;
    private Player ignis;

    private Screen screen;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);

            aqua = new Aqua(10,10);
            ignis = new Ignis(10,11);

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

        TextGraphics textGraphics = screen.newTextGraphics();
        aqua.draw(textGraphics);
        ignis.draw(textGraphics);

        screen.refresh();

    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
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

            //fire boy mexia com setinhas e water girl com awsd :)
            if (key.getKeyType() == KeyType.ArrowUp) ignis.setPosition(ignis.moveUp());
            if (key.getKeyType() == KeyType.ArrowDown) ignis.setPosition(ignis.moveDown());
            if (key.getKeyType() == KeyType.ArrowLeft) ignis.setPosition(ignis.moveLeft());
            if (key.getKeyType() == KeyType.ArrowRight) ignis.setPosition(ignis.moveRight());

            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'w' || key.getCharacter() == 'W')) aqua.setPosition(aqua.moveUp());
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S')) aqua.setPosition(aqua.moveDown());
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'a' || key.getCharacter() == 'A')) aqua.setPosition(aqua.moveLeft());
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'd' || key.getCharacter() == 'D')) aqua.setPosition(aqua.moveRight());

            processKey(key);
            draw();
        }
    }

}