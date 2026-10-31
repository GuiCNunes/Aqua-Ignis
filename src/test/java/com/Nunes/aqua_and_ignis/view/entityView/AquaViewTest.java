package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import com.Nunes.aqua_and_ignis.view.entityView.AquaView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static org.mockito.Mockito.*;

class AquaViewTest {
    private GUI gui;
    private Player player;
    private AquaView view;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        player = mock(Player.class);
        view = new AquaView();
    }

    @Test
    void draw() {
        when(player.getPosition()).thenReturn(new Position(5, 10));

        view.draw(player, gui);

        verify(gui).fillRect(5, 10, 1, 1, Colors.AQUA);
    }
}