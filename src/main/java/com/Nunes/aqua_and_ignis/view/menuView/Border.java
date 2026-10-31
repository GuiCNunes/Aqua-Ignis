package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

public final class Border {

    public static void draw(GUI gui) {
        int right = gui.getWidth() - 1;
        int bottom = gui.getHeight() - 1;

        for (int x = 1; x < right; x++) {
            drawTile(gui, x, 0, Tile.WALL_10);
            drawTile(gui, x, bottom, Tile.WALL_10);
        }
        for (int y = 1; y < bottom; y++) {
            drawTile(gui, 0, y, Tile.WALL_5);
            drawTile(gui, right, y, Tile.WALL_5);
        }
        drawTile(gui, 0, 0, Tile.WALL_6);
        drawTile(gui, right, 0, Tile.WALL_12);
        drawTile(gui, 0, bottom, Tile.WALL_3);
        drawTile(gui, right, bottom, Tile.WALL_9);
    }

    private static void drawTile(GUI gui, int x, int y, Tile tile) {
        gui.drawTile(x, y, tile, Colors.WALL_BRICK, Colors.WALL);
    }

    private Border() {}
}
