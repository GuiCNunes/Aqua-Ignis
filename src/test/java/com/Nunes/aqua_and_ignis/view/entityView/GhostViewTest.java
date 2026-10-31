package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GhostViewTest {

    @Test
    void draw() {
        GUI gui = mock(GUI.class);
        Monster monster = mock(Monster.class);
        GhostView ghostView = new GhostView();

        Position position = new Position(2, 3);
        when(monster.getPosition()).thenReturn(position);

        ghostView.draw(monster, gui, Colors.FLOOR, 0);

        verify(gui, times(1)).drawTile(3, 5, Tile.GHOST, Colors.GHOST, Colors.FLOOR);
    }

    @Test
    void drawSecondFrame() {
        GUI gui = mock(GUI.class);
        Monster monster = mock(Monster.class);
        when(monster.getPosition()).thenReturn(new Position(2, 3));

        new GhostView().draw(monster, gui, Colors.FLOOR, 1);

        verify(gui).drawTile(3, 5, Tile.GHOST_2, Colors.GHOST, Colors.FLOOR);
    }
}
