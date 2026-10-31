package com.Nunes.aqua_and_ignis.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGUITest {
    private LanternaGUI gui;
    private TerminalScreen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws Exception {
        screen = mock(TerminalScreen.class);
        graphics = mock(TextGraphics.class);

        gui = new LanternaGUI(90, 30, 16);

        Field field = LanternaGUI.class.getDeclaredField("screen");
        field.setAccessible(true);
        field.set(gui, screen);

        when(screen.newTextGraphics()).thenReturn(graphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(90, 30));
    }

    @Test
    void readInput() throws IOException {
        KeyStroke expected = new KeyStroke(KeyType.Enter);
        when(screen.readInput()).thenReturn(expected);
        KeyStroke result = gui.readInput();
        assertEquals(expected, result);
    }

    @Test
    void getOperationBasic() throws IOException {
        when(screen.pollInput()).thenReturn(null);
        assertEquals(GUI.operation.none, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.EOF));
        assertEquals(GUI.operation.quit, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke('q', false, false));
        assertEquals(GUI.operation.quit, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.Enter));
        assertEquals(GUI.operation.select, gui.getOperation());
    }

    @Test
    void getOperationWASD() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke('w', false, false));
        assertEquals(GUI.operation.w, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke('S', false, false));
        assertEquals(GUI.operation.s, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke('a', false, false));
        assertEquals(GUI.operation.a, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke('D', false, false));
        assertEquals(GUI.operation.d, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke('z', false, false));
        assertEquals(GUI.operation.none, gui.getOperation());
    }

    @Test
    void getOperationArrows() throws IOException {
        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
        assertEquals(GUI.operation.up, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        assertEquals(GUI.operation.down, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        assertEquals(GUI.operation.left, gui.getOperation());

        when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        assertEquals(GUI.operation.right, gui.getOperation());
    }

    @Test
    void lifecycle() throws IOException {
        gui.startScreen();
        verify(screen).startScreen();
        verify(screen).setCursorPosition(null);

        gui.stopScreen();
        verify(screen).stopScreen();

        gui.clear();
        verify(screen).clear();

        gui.refresh();
        verify(screen).refresh();
    }

    @Test
    void drawPixel() {
        Color color = new Color(255, 0, 0);
        gui.drawPixel(0, 0, color);
        gui.drawPixel(89, 29, color);
        verify(graphics, times(2)).putString(anyInt(), anyInt(), eq(" "));

        reset(graphics);
        gui.drawPixel(-1, 0, color);
        gui.drawPixel(90, 0, color);
        gui.drawPixel(0, -1, color);
        gui.drawPixel(0, 30, color);
        verify(graphics, never()).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void drawString() {
        Color fg = new Color(255, 255, 255);
        Color bg = new Color(0, 0, 0);

        gui.drawString(0, 0, "text", fg, bg);
        verify(graphics).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
        verify(graphics).putString(0, 0, "text");

        reset(graphics);
        gui.drawString(0, 0, "text", null, null);
        verify(graphics, never()).setForegroundColor(any());
        verify(graphics, never()).setBackgroundColor(any());
        verify(graphics).putString(0, 0, "text");

        reset(graphics);
        gui.drawString(-1, 0, "out", fg, bg);
        gui.drawString(90, 0, "out", fg, bg);
        gui.drawString(0, -1, "out", fg, bg);
        gui.drawString(0, 30, "out", fg, bg);
        verify(graphics, never()).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void drawText() {
        Color fg = new Color(255, 255, 255);
        Color bg = new Color(0, 0, 0);

        gui.drawText(0, 0, "text", fg, null);
        verify(graphics).setForegroundColor(any());
        verify(graphics, never()).setBackgroundColor(any());

        reset(graphics);
        gui.drawText(0, 0, "text", null, bg);
        verify(graphics, never()).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
    }

    @Test
    void drawTile() {
        Color fg = new Color(255, 255, 255);
        Color bg = new Color(0, 0, 0);

        gui.drawTile(3, 4, Tile.WALL, fg, bg);
        verify(graphics).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
        verify(graphics).putString(3, 4, String.valueOf(Tile.WALL.glyph()));

        reset(graphics);
        gui.drawTile(-1, 0, Tile.WALL, fg, bg);
        gui.drawTile(90, 0, Tile.WALL, fg, bg);
        gui.drawTile(0, -1, Tile.WALL, fg, bg);
        gui.drawTile(0, 30, Tile.WALL, fg, bg);
        verify(graphics, never()).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void dimensions() {
        assertEquals(90, gui.getWidth());
        assertEquals(30, gui.getHeight());
    }

    @Test
    void fillRect() {
        Color color = new Color(255, 255, 255);
        gui.fillRect(0, 0, 10, 10, color);
        verify(graphics).fillRectangle(any(), any(), eq(' '));
    }
}