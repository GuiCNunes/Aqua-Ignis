package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static com.Nunes.aqua_and_ignis.view.Scale.SCALE;


public class SlimeView implements EntityView<Monster> {
    @Override
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();

        gui.fillTile(position.getX(), position.getY(), Colors.SLIME);
    }
}
