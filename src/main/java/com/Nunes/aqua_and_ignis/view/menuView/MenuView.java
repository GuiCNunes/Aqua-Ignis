package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

public class MenuView extends View<Menu> {
    public MenuView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui) {
        Border.draw(gui);
        Menu menu = getModel();
        drawMenu(menu, gui);
    }


    private void drawMenu(Menu menu, GUI gui) {

        gui.drawTile(3, 5, Tile.AQUA, Colors.AQUA, Colors.BLACK);
        gui.drawTile(18, 5, Tile.IGNIS, Colors.IGNIS, Colors.BLACK);
        gui.drawString(5, 5, "Aqua", Colors.AQUA, null);
        gui.drawString(10, 5, "&", Colors.GREEN, null);
        gui.drawString( 12, 5, "Ignis", Colors.IGNIS, null);
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            Color color = menu.isSelected(i) ? Colors.GOLD : Colors.WHITE;
            gui.drawString(5, 7 + i, menu.getEntry(i), color, null);
        }
    }
}
