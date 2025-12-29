package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.model.entity.Aqua;
import com.Nunes.aqua_and_ignis.model.entity.Ignis;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import com.Nunes.aqua_and_ignis.gui.GUI;

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
