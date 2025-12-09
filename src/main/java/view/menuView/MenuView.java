package view.menuView;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import gui.GUI;
import model.Position;
import model.menu.Menu;
import view.View;

public class MenuView extends View<Menu> {
    public MenuView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui) {
        Menu menu = getModel();

        TextGraphics g = gui.newTextGraphics();

        drawMenu(menu, g);
    }

    public void drawText(Position position, String text, String color, TextGraphics g) {
        g.setForegroundColor(TextColor.Factory.fromString(color));
        g.putString(position.getX(), position.getY(), text);
    }

    private void drawMenu(Menu menu, TextGraphics g) {
        drawText(new Position(5, 5), "Menu", "#FFFFFF", g);
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            drawText(new Position(5, 7 + i), getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF", g);
    }


}
