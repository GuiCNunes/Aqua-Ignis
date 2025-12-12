package gui;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class LanternaGUI implements GUI {

    private Screen screen;
    private int width;
    private int height;

    public LanternaGUI(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();

        this.screen = new TerminalScreen(terminal);

        this.width = terminal.getTerminalSize().getColumns();
        this.height = terminal.getTerminalSize().getRows();
    }
    @Override
    public void startScreen() throws IOException {
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    @Override
    public void stopScreen() throws IOException {
        screen.stopScreen();
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public KeyStroke readInput() throws IOException {
        return screen.readInput();
    }

    @Override
    public TextGraphics newTextGraphics() {
        return screen.newTextGraphics();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public operation getOperation() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key == null) return operation.none;

        if(key.getKeyType() == KeyType.EOF) return operation.quit;
        else if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') return operation.quit;

        if(key.getKeyType() == KeyType.ArrowUp) return operation.up;
        else if(key.getKeyType() == KeyType.ArrowDown) return operation.down;
        else if(key.getKeyType() == KeyType.ArrowLeft) return operation.left;
        else if(key.getKeyType() == KeyType.ArrowRight) return operation.right;

        if(key.getKeyType() == KeyType.Character){
            char c = Character.toLowerCase(key.getCharacter());

            switch(c){
                case 'w': return operation.w;
                case 's': return operation.s;
                case 'a': return operation.a;
                case 'd': return operation.d;
            }
        }

        if(key.getKeyType() == KeyType.Enter) return operation.select;

        return operation.none;
    }
}
