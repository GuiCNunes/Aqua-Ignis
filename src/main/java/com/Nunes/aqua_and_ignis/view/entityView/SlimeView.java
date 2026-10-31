package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static com.Nunes.aqua_and_ignis.view.Layout.worldX;
import static com.Nunes.aqua_and_ignis.view.Layout.worldY;

public class SlimeView {
    public void draw(Monster monster, GUI gui, Color background, int frame) {
        Position position = monster.getPosition();

        Tile tile = frame == 0 ? Tile.SLIME : Tile.SLIME_2;

        gui.drawTile(worldX(position.getX()), worldY(position.getY()), tile, Colors.SLIME, background);
    }
}
