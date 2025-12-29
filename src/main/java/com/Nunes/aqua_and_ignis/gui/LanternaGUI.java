package com.Nunes.aqua_and_ignis.gui;

import java.io.IOException;
import java.awt.Font;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

public class LanternaGUI implements GUI {

    private Screen screen;
    private int width;
    private int height;

    public LanternaGUI(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setTerminalEmulatorFontConfiguration(loadSquareFontCfg1());

        AWTTerminalFrame terminal = terminalFactory.createAWTTerminal();
        terminal.setTitle("Aqua & Ignis");
        terminal.setResizable(false);
        terminal.pack();
        terminal.setVisible(true);
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
    public int getWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public int getHeight() {
        return screen.getTerminalSize().getRows();
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

    @Override
    public void drawPixel(int x, int y, Color color) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        var g = screen.newTextGraphics();
        g.setBackgroundColor(toLanterna(color));
        g.putString(x, y, " ");
    }

    @Override
    public void drawString(int x, int y, String s, Color fg, Color bg) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        var g = screen.newTextGraphics();
        if (bg != null) g.setBackgroundColor(toLanterna(bg));
        if (fg != null) g.setForegroundColor(toLanterna(fg));
        g.putString(x, y, s);
    }

    @Override
    public void fillRect(int x, int y, int w, int h, Color color) {
        var g = screen.newTextGraphics();
        g.setBackgroundColor(toLanterna(color));
        g.fillRectangle(
                new com.googlecode.lanterna.TerminalPosition(x, y),
                new com.googlecode.lanterna.TerminalSize(w, h),
                ' '
        );
    }

    @Override
    public void drawText(int x, int y, String text, Color fg, Color bg) {
        var g = screen.newTextGraphics();
        if (bg != null) g.setBackgroundColor(toLanterna(bg));
        if (fg != null) g.setForegroundColor(toLanterna(fg));
        g.putString(x, y, text);
    }

    private TextColor toLanterna(Color c) {
        return new TextColor.RGB(c.r(), c.g(), c.b());
    }


    private AWTTerminalFontConfiguration loadSquareFontCfg1() {
        Font font = new Font("Monospaced", Font.PLAIN, 16);
        return AWTTerminalFontConfiguration.newInstance(font);
    }


}

