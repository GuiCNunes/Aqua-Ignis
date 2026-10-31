package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Player;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

import static com.Nunes.aqua_and_ignis.view.Layout.worldX;
import static com.Nunes.aqua_and_ignis.view.Layout.worldY;

public class AquaView {
    public void draw(Player player, GUI gui, Color background, int frame) {
        Position position = player.getPosition();

        Tile tile = frame == 0 ? Tile.AQUA : Tile.AQUA_2;

        gui.drawTile(worldX(position.getX()), worldY(position.getY()), tile, Colors.AQUA, background);
    }
}
