package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.View;

public class LoseView extends View<Menu> {
    public LoseView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui){
        Border.draw(gui);
        gui.drawText(10,10, "GAME OVER!",
                Colors.KEY , Colors.BG_UI);
        gui.drawText(10,12, "YOU DIED",
                Colors.WHITE, Colors.BG_UI);
        gui.drawText(10,13, "Press enter to go back to main menu",
                Colors.YELLOW, Colors.BG_UI);
    }
}
