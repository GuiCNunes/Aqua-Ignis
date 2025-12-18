package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.menu.Menu;

public class LoseView extends View<Menu>{
    public LoseView(Menu menu) {
        super(menu);
    }
    @Override
    public void drawElements(GUI gui){
        gui.drawText(10,10, "GAME OVER!",
                TextColor.Factory.fromString("#FF0000"), TextColor.Factory.fromString("#2C2C2C"));
        gui.drawText(10,12, "YOU DIED",
                TextColor.Factory.fromString("#FFFFFF"), TextColor.Factory.fromString("#2C2C2C"));
        gui.drawText(10,13, "Press enter to go back to main menu",
                TextColor.Factory.fromString("#FFFF00"), TextColor.Factory.fromString("#2C2C2C"));
    }
}
