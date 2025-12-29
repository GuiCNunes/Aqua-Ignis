package view.menuView;

import gui.GUI;
import model.menu.Menu;
import view.Theme.Colors;
import view.View;



public class HelpView extends View<Menu> {
    public HelpView(Menu menu) {
            super(menu);
        }
        @Override
        public void drawElements(GUI gui){
            gui.drawText(5, 5, "OBJECTIVE:", Colors.TITLE, Colors.BG_UI);
            gui.drawText(5, 7, "Work together to collect 3 STARS and find the KEY.", Colors.WHITE, Colors.BG_UI);
            gui.drawText(5, 8, "Both players must reach the EXIT once it is open!", Colors.WHITE, Colors.BG_UI);
            gui.drawText(5, 11, "CONTROLS:", Colors.TITLE, Colors.BG_UI);

            gui.drawText(5, 13, "AQUA:", Colors.AQUA, Colors.BG_UI);
            gui.drawText(20, 13, "W A S D to Move", Colors.WHITE, Colors.BG_UI);

            gui.drawText(5, 14, "IGNIS:", Colors.IGNIS, Colors.BG_UI);
            gui.drawText(20, 14, "ARROW KEYS to Move", Colors.WHITE, Colors.BG_UI);

            gui.drawText(5, 18, "Press ENTER to return to Main Menu", Colors.STAR, Colors.BG_UI);
        }
}


