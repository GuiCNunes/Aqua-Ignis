package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.menu.Menu;

public class WinView extends View<Menu>{
    private final int stars;
    private final long time;

    public WinView(Menu menu, int stars, long time) {
        super(menu);
        this.stars = stars;
        this.time = time;
    }
    @Override
    public void drawElements(GUI gui){
        TextColor bg = TextColor.Factory.fromString("#2C2C2C");
        gui.drawText(10,10, "CONGRATULATIONS!",
                TextColor.Factory.fromString("#00FF00"), bg);
        gui.drawText(10,12, "YOU ESCAPED THE MAZE",
                TextColor.Factory.fromString("#FFFFFF"), bg);
        gui.drawText(10,14, "Press enter to return to main menu",
                TextColor.Factory.fromString("#FFFF00"), bg);
        gui.drawText(10, 12, "Stars Collected: " + stars + "/3", TextColor.Factory.fromString("#FFFF00"), bg);
        gui.drawText(10, 13, "Total Time: " + time + "s", TextColor.Factory.fromString("#FFFFFF"),
                bg);
    }
}
