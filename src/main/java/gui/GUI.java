package gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public interface GUI {

    void startScreen() throws IOException;
    void stopScreen() throws IOException;
    void clear();
    void refresh() throws IOException;

    KeyStroke readInput() throws IOException;

    TextGraphics newTextGraphics();

    int getWidth();
    int getHeight();

    enum operation{up, down, left, right, quit, none}

    operation getOperation() throws IOException;
}
