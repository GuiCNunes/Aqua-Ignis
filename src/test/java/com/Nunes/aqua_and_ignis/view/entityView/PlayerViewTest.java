package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.model.entity.Aqua;
import com.Nunes.aqua_and_ignis.model.entity.Ignis;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class PlayerViewTest {
    private PlayerView playerView;
    private IgnisView ignisView;
    private AquaView aquaView;
    private GUI gui;

    @BeforeEach
    void setUp() throws Exception {
        playerView = new PlayerView();
        ignisView = mock(IgnisView.class);
        aquaView = mock(AquaView.class);
        gui = mock(GUI.class);

        Field ignisField = PlayerView.class.getDeclaredField("ignisView");
        ignisField.setAccessible(true);
        ignisField.set(playerView, ignisView);

        Field aquaField = PlayerView.class.getDeclaredField("aquaView");
        aquaField.setAccessible(true);
        aquaField.set(playerView, aquaView);
    }

    @Test
    void drawAqua() {
        Aqua aqua = mock(Aqua.class);

        playerView.draw(aqua, gui, Colors.FLOOR);

        verify(aquaView, times(1)).draw(aqua, gui, Colors.FLOOR);
        verify(ignisView, never()).draw(any(), any(), any());
    }

    @Test
    void drawIgnis() {
        Ignis ignis = mock(Ignis.class);

        playerView.draw(ignis, gui, Colors.FLOOR);

        verify(ignisView, times(1)).draw(ignis, gui, Colors.FLOOR);
        verify(aquaView, never()).draw(any(), any(), any());
    }

    @Test
    void drawUnknownPlayer() {
        Player player = mock(Player.class);

        playerView.draw(player, gui, Colors.FLOOR);

        verify(aquaView, never()).draw(any(), any(), any());
        verify(ignisView, never()).draw(any(), any(), any());
    }
}