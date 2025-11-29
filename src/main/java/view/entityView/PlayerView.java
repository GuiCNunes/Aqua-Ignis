package view.entityView;

import model.entity.Aqua;
import model.entity.Ignis;
import model.entity.Player;
import com.googlecode.lanterna.graphics.TextGraphics;

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
