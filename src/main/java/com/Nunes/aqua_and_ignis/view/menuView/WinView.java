package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;

public class WinView extends View<Menu> {
    private final int stars;
    private final long time;

    public WinView(Menu menu, int stars, long time) {
        super(menu);
        this.stars = stars;
        this.time = time;
    }
    @Override
    public void drawElements(GUI gui){
        gui.drawText(10,10, "CONGRATULATIONS!",
                Colors.GREEN, Colors.BG_UI);
        gui.drawText(10,12, "YOU ESCAPED THE MAZE",
                Colors.WHITE, Colors.BG_UI);
        gui.drawText(10,14, "Press enter to return to main menu",
                Colors.YELLOW, Colors.BG_UI);
        gui.drawText(10, 12, "Stars Collected: " + stars + "/3", Colors.YELLOW, Colors.BG_UI);
        gui.drawText(10, 13, "Total Time: " + time + "s", Colors.WHITE, Colors.BG_UI);

    }
}
