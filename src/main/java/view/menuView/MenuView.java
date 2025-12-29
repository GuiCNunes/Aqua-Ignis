package view.menuView;

import gui.Color;
import gui.GUI;
import model.menu.Menu;
import view.Theme.Colors;
import view.View;

public class MenuView extends View<Menu> {
    public MenuView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui) {
        Menu menu = getModel();
        drawMenu(menu, gui);
    }


    private void drawMenu(Menu menu, GUI gui) {

        gui.drawString(5, 5, "Aqua", Colors.AQUA, null);
        gui.drawString(10, 5, "&", Colors.GREEN, null);
        gui.drawString( 12, 5, "Ignis", Colors.IGNIS, null);
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            Color color = menu.isSelected(i) ? Colors.GOLD : Colors.WHITE;
            gui.drawString(5, 7 + i, menu.getEntry(i), color, null);
        }
    }
}
