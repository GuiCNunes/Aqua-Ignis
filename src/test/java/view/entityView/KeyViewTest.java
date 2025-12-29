package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Scale;
import view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class KeyViewTest {
    private GUI gui;
    private Key key;
    private KeyView view;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        key = mock(Key.class);
        view = new KeyView();
        Scale.setScale(2);
    }

    @Test
    void draw() {
        when(key.getPosition()).thenReturn(new Position(3, 7));

        view.draw(gui, key);

        verify(gui).fillRect(6, 14, 2, 2, Colors.KEY);
    }

    @Test
    void drawElements() throws IOException {
        view.drawElements(gui);
        verifyNoInteractions(gui);
    }
}