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

    private int aqua_x = 10;
    private int aqua_y = 10;

    private int ignis_x = 11;
    private int ignis_y = 10;

    private Screen screen;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);



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
        screen.setCharacter(aqua_x, aqua_y, TextCharacter.fromCharacter('X')[0]);
        screen.setCharacter(ignis_x,ignis_y,TextCharacter.fromCharacter('O')[0]);
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
            if (key.getKeyType() == KeyType.ArrowUp) ignis_y -= 1;
            if (key.getKeyType() == KeyType.ArrowDown) ignis_y += 1;
            if (key.getKeyType() == KeyType.ArrowLeft) ignis_x -= 1;
            if (key.getKeyType() == KeyType.ArrowRight) ignis_x += 1;

            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'w' || key.getCharacter() == 'W')) aqua_y -= 1;
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S')) aqua_y += 1;
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'a' || key.getCharacter() == 'A')) aqua_x -= 1;
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'd' || key.getCharacter() == 'D')) aqua_x += 1;

            processKey(key);
            draw();
        }
    }

}