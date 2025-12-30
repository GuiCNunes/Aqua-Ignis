package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Star;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;

public class StarView implements EntityView<Star> {
    @Override
    public void draw( Star star, GUI gui) {

        Position position = star.getPosition();

        gui.fillTile(position.getX(), position.getY(), Colors.STAR);
    }
}
