package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Scale;
import view.Theme.Colors;

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