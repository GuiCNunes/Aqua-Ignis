package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        gui = new LanternaGUI(90, 30);
        screen = mock(TerminalScreen.class);
        graphics = mock(TextGraphics.class);

        Field field = LanternaGUI.class.getDeclaredField("screen");
        field.setAccessible(true);
        field.set(gui, screen);

        when(screen.newTextGraphics()).thenReturn(graphics);
        when(screen.getTerminalSize()).thenReturn(new TerminalSize(90, 30));
    }

    @Test
    void testLifecycle() throws IOException {
        gui.startScreen();
        verify(screen).startScreen();
        verify(screen).setCursorPosition(null);
        verify(screen).doResizeIfNecessary();

        gui.stopScreen();
        verify(screen).stopScreen();

        gui.clear();
        verify(screen).clear();

        gui.refresh();
        verify(screen).refresh();
    }

    @Test
    void testDimensions() {
        assertEquals(90, gui.getWidth());
        assertEquals(30, gui.getHeight());
    }

    @Test
    void testReadInput() throws IOException {
        KeyStroke stroke = new KeyStroke(KeyType.Enter);
        when(screen.readInput()).thenReturn(stroke);
        assertEquals(stroke, gui.readInput());
    }

    @Test
    void testGetOperationBasic() throws IOException {
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
    void testGetOperationArrows() throws IOException {
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
    void testGetOperationWASD() throws IOException {
        char[] keys = {'w', 's', 'a', 'd', 'W', 'S', 'A', 'D'};
        GUI.operation[] results = {
                GUI.operation.w, GUI.operation.s, GUI.operation.a, GUI.operation.d,
                GUI.operation.w, GUI.operation.s, GUI.operation.a, GUI.operation.d
        };

        for (int i = 0; i < keys.length; i++) {
            when(screen.pollInput()).thenReturn(new KeyStroke(keys[i], false, false));
            assertEquals(results[i], gui.getOperation());
        }

        when(screen.pollInput()).thenReturn(new KeyStroke('x', false, false));
        assertEquals(GUI.operation.none, gui.getOperation());
    }

    @Test
    void testDrawPixel() {
        Color color = new Color(255, 0, 0);

        gui.drawPixel(1, 1, color);
        verify(graphics).putString(1, 1, " ");

        gui.drawPixel(-1, 0, color);
        gui.drawPixel(0, -1, color);
        gui.drawPixel(90, 0, color);
        gui.drawPixel(0, 30, color);
        verify(graphics, times(1)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void testDrawString() {
        Color fg = new Color(255, 255, 255);
        Color bg = new Color(0, 0, 0);

        gui.drawString(0, 0, "test", fg, bg);
        verify(graphics).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
        verify(graphics).putString(0, 0, "test");

        gui.drawString(-1, 0, "test", fg, bg);
        gui.drawString(0, -1, "test", fg, bg);
        gui.drawString(90, 0, "test", fg, bg);
        gui.drawString(0, 30, "test", fg, bg);
        verify(graphics, times(1)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    void testDrawStringColorBranches() {
        Color color = new Color(1, 1, 1);

        gui.drawString(0, 0, "test", color, null);
        verify(graphics).setForegroundColor(any());
        verify(graphics, times(0)).setBackgroundColor(any());

        reset(graphics);
        gui.drawString(0, 0, "test", null, color);
        verify(graphics, times(0)).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
    }

    @Test
    void testDrawTextBranches() {
        Color fg = new Color(255, 255, 255);
        Color bg = new Color(0, 0, 0);

        gui.drawText(0, 0, "text", fg, null);
        verify(graphics).setForegroundColor(any());
        verify(graphics, times(0)).setBackgroundColor(any());

        reset(graphics);
        gui.drawText(0, 0, "text", null, bg);
        verify(graphics, times(0)).setForegroundColor(any());
        verify(graphics).setBackgroundColor(any());
    }

    @Test
    void testFillRect() {
        Color color = new Color(255, 255, 255);
        gui.fillRect(0, 0, 5, 5, color);
        verify(graphics).fillRectangle(any(), any(), eq(' '));
    }
}