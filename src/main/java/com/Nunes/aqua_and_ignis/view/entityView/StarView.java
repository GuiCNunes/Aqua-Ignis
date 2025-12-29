package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;

public class StarView extends View<Star> {
    public StarView() {
        super(null);
    }
    @Override
    public void drawElements(GUI gui) {}

    public void draw(GUI gui, Star star) {

        Position position = star.getPosition();

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.STAR);
    }
}
