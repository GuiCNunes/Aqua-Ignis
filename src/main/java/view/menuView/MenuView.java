package view.menu;

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

    private void drawMenu(Menu menu, TextGraphics g) {



    }


}
