package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.menu.Menu;

public class WinView extends View<Menu>{
    public WinView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui){
        gui.drawText(10,10, "CONGRATULATIONS!",
                TextColor.Factory.fromString("#00FF00"), TextColor.Factory.fromString("#2C2C2C"));
        gui.drawText(10,12, "YOU ESCAPED THE MAZE",
                TextColor.Factory.fromString("#FFFFFF"), TextColor.Factory.fromString("#2C2C2C"));
        gui.drawText(10,14, "Press enter to return to main menu",
                TextColor.Factory.fromString("#FFFF00"), TextColor.Factory.fromString("#2C2C2C"));
    }
}
