package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import com.Nunes.aqua_and_ignis.view.entityView.StarView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

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
    }

    @Test
    void draw() {
        when(star.getPosition()).thenReturn(new Position(10, 5));

        view.draw(gui, star);

        verify(gui).fillRect(10, 5, 1, 1, Colors.STAR);
    }
}