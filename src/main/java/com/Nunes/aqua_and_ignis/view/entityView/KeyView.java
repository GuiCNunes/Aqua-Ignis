package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Key;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;

public class KeyView implements EntityView<Key> {
    @Override
    public void draw(Key key , GUI gui) {

        Position position = key.getPosition();

        gui.fillTile(position.getX(), position.getY(), Colors.KEY);
    }
}
