package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SlimeViewTest {

    @Test
    void draw() {
        GUI gui = mock(GUI.class);
        Monster monster = mock(Monster.class);
        SlimeView slimeView = new SlimeView();

        Position position = new Position(5, 8);
        when(monster.getPosition()).thenReturn(position);

        slimeView.draw(monster, gui, Colors.FLOOR, 0);

        verify(gui, times(1)).drawTile(6, 10, Tile.SLIME, Colors.SLIME, Colors.FLOOR);
    }

    @Test
    void drawSecondFrame() {
        GUI gui = mock(GUI.class);
        Monster monster = mock(Monster.class);
        when(monster.getPosition()).thenReturn(new Position(5, 8));

        new SlimeView().draw(monster, gui, Colors.FLOOR, 1);

        verify(gui).drawTile(6, 10, Tile.SLIME_2, Colors.SLIME, Colors.FLOOR);
    }
}
