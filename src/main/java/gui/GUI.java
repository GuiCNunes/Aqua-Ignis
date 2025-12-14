package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public interface GUI {

    void startScreen() throws IOException;
    void stopScreen() throws IOException;
    void clear();
    void refresh() throws IOException;

    KeyStroke readInput() throws IOException;

    int getWidth();
    int getHeight();

    enum operation{up, down, left, right, w, a, s, d, quit, none, select}

    operation getOperation() throws IOException;

    void drawPixel(int x, int y, TextColor color);

    void drawString(int x, int y, String s, TextColor fg, TextColor bg);

    void fillRect(int x, int y, int width, int height, TextColor color);

    void drawText(int x, int y, String text, TextColor fg, TextColor bg);
}
