package view;

import model.entity.Aqua;
import model.entity.Ignis;
import model.entity.Player;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import model.Position;

public class PlayerView {
    private IgnisView ignisView = new IgnisView();
    private AquaView aquaView = new AquaView();

    public void draw(Player player, TextGraphics g) {
        if (player instanceof Aqua) {
            aquaView.draw(player, g);
        }
        else if (player instanceof Ignis) {
            ignisView.draw(player, g);
        }
    }
}
