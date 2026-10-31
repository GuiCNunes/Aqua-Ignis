package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Key;
import com.Nunes.aqua_and_ignis.view.entityView.KeyView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

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
    }

    @Test
    void draw() {
        when(key.getPosition()).thenReturn(new Position(3, 7));

        view.draw(gui, key, Colors.FLOOR);

        verify(gui).drawTile(4, 9, Tile.KEY, Colors.KEY, Colors.FLOOR);
    }
}