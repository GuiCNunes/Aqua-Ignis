package com.Nunes.aqua_and_ignis.view;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ViewTest {
    private GUI gui;
    private Object model;
    private View<Object> view;

    private static class ViewStub extends View<Object> {
        public ViewStub(Object model) {
            super(model);
        }

        @Override
        protected void drawElements(GUI gui) throws IOException {
            gui.drawText(0, 0, "test", null, null);
        }
    }

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        model = new Object();
        view = new ViewStub(model);
    }

    @Test
    void getModel() {
        assertEquals(model, view.getModel());
    }

    @Test
    void draw() throws IOException {
        view.draw(gui);

        verify(gui).clear();
        verify(gui).drawText(0, 0, "test", null, null);
        verify(gui).refresh();
    }
}