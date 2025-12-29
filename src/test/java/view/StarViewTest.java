package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Star;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Scale;
import view.Theme.Colors;

import java.io.IOException;

import static org.mockito.Mockito.*;

class StarViewTest {
    private GUI gui;
    private Star star;
    private StarView view;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        star = mock(Star.class);
        view = new StarView();
        Scale.setScale(2);
    }

    @Test
    void draw() {
        when(star.getPosition()).thenReturn(new Position(10, 5));

        view.draw(gui, star);

        verify(gui).fillRect(20, 10, 2, 2, Colors.STAR);
    }

    @Test
    void drawElements() throws IOException {
        view.drawElements(gui);
        verifyNoInteractions(gui);
    }
}