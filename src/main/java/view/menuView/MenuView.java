package view.menuView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.menu.Menu;
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


    private static final TextColor WHITE = TextColor.Factory.fromString("#FFFFFF");
    private static final TextColor GOLD  = TextColor.Factory.fromString("#FFD700");

    private void drawMenu(Menu menu, GUI gui) {

        gui.drawString(5, 5, "Aqua", TextColor.Factory.fromString("#00BFFF"), null);
        gui.drawString(10, 5, "&", TextColor.Factory.fromString("#00FF00"), null);
        gui.drawString( 12, 5, "Ignis", TextColor.Factory.fromString("#FFA500"), null);
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            TextColor color = menu.isSelected(i) ? GOLD : WHITE;
            gui.drawString(5, 7 + i, menu.getEntry(i), color, null);
        }
    }
}
