package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

public class StarView {

    public void draw(GUI gui, Star star) {

        Position position = star.getPosition();

        gui.fillRect(position.getX(), position.getY(), 1, 1, Colors.STAR);
    }
}
