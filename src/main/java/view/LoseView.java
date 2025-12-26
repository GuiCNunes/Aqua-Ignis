package view;

import view.Colors;
import gui.GUI;
import model.menu.Menu;

public class LoseView extends View<Menu>{
    public LoseView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui){
        gui.drawText(10,10, "GAME OVER!",
                Colors.KEY , Colors.BG_UI);
        gui.drawText(10,12, "YOU DIED",
                Colors.WHITE, Colors.BG_UI);
        gui.drawText(10,13, "Press enter to go back to main menu",
                Colors.YELLOW, Colors.BG_UI);
    }
}
