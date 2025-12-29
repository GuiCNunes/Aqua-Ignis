package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Scale;
import view.Theme.Colors;

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
        Scale.setScale(2);
    }

    @Test
    void draw() {
        when(player.getPosition()).thenReturn(new Position(5, 10));

        view.draw(player, gui);

        verify(gui).fillRect(10, 20, 2, 2, Colors.AQUA);
    }
}