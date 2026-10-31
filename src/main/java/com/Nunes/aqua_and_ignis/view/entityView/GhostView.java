package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

public class GhostView {
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();

        gui.fillRect(position.getX(), position.getY(), 1, 1, Colors.GHOST);
    }
}