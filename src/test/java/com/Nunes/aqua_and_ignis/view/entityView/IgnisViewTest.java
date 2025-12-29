package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import com.Nunes.aqua_and_ignis.view.entityView.IgnisView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Scale;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static org.mockito.Mockito.*;

class IgnisViewTest {
    private GUI gui;
    private Player player;
    private IgnisView view;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        player = mock(Player.class);
        view = new IgnisView();
        Scale.setScale(3);
    }

    @Test
    void draw() {
        when(player.getPosition()).thenReturn(new Position(2, 4));

        view.draw(player, gui);

        verify(gui).fillRect(6, 12, 3, 3, Colors.IGNIS);
    }
}