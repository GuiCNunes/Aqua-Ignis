package view.entityView;

import model.entity.Aqua;
import model.entity.Ignis;
import model.entity.Player;
import gui.GUI;

public class PlayerView {
    private IgnisView ignisView = new IgnisView();
    private AquaView aquaView = new AquaView();

    public void draw(Player player, GUI gui) {
        if (player instanceof Aqua) {
            aquaView.draw(player, gui);
        }
        else if (player instanceof Ignis) {
            ignisView.draw(player, gui);
        }
    }
}
