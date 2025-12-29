package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;


public class IgnisView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();
        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.IGNIS);
    }
}