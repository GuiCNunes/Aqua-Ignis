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

    private Space space;

    private Screen screen;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(60, 30);

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            Terminal terminal = terminalFactory.createTerminal();

            this.screen = new TerminalScreen(terminal);

            TerminalSize size = terminal.getTerminalSize();

            this.space = new Space(size.getColumns(), size.getRows());

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

        space.draw(screen.newTextGraphics());

        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        space.processKey(key);
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