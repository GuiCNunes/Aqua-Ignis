package gui;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

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
}
