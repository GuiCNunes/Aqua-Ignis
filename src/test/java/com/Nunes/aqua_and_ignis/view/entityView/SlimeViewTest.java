package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
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

        slimeView.draw(monster, gui);

        verify(gui, times(1)).fillRect(6, 10, 1, 1, Colors.SLIME);
    }
}