package view.menuView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.menu.Menu;
import view.View;



public class HelpView extends View<Menu> {
    public HelpView(Menu menu) {
            super(menu);
        }
        @Override
        public void drawElements(GUI gui){
            TextColor titleColor = TextColor.Factory.fromString("#00FFFF");
            TextColor textColor = TextColor.Factory.fromString("#FFFFFF");
            TextColor highlight = TextColor.Factory.fromString("#FFFF00");
            TextColor bg = TextColor.Factory.fromString("#2C2C2C");
            gui.drawText(5, 5, "OBJECTIVE:", titleColor, bg);
            gui.drawText(5, 7, "Work together to collect 3 STARS and find the KEY.", textColor, bg);
            gui.drawText(5, 8, "Both players must reach the EXIT once it is open!", textColor, bg);
            gui.drawText(5, 11, "CONTROLS:", titleColor, bg);

            gui.drawText(5, 13, "AQUA:", TextColor.Factory.fromString("#0000FF"), bg);
            gui.drawText(20, 13, "W A S D to Move", textColor, bg);

            gui.drawText(5, 14, "IGNIS:", TextColor.Factory.fromString("#FF0000"), bg);
            gui.drawText(20, 14, "ARROW KEYS to Move", textColor, bg);

            gui.drawText(5, 18, "Press ENTER to return to Main Menu", highlight, bg);
        }
}


