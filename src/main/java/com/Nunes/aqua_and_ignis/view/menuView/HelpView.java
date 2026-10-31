package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.gui.Tile;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import com.Nunes.aqua_and_ignis.view.View;



public class HelpView extends View<Menu> {
    public HelpView(Menu menu) {
            super(menu);
        }
        @Override
        public void drawElements(GUI gui){
            Border.draw(gui);
            drawSprites(gui);
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

        private void drawSprites(GUI gui) {
            gui.drawTile(2, 7, Tile.STAR, Colors.STAR, Colors.BG_UI);
            gui.drawTile(3, 7, Tile.KEY, Colors.KEY, Colors.BG_UI);
            gui.drawTile(3, 8, Tile.DOOR_OPEN, Colors.DOOR_OPEN, Colors.BG_UI);
            gui.drawTile(3, 13, Tile.AQUA, Colors.AQUA, Colors.BG_UI);
            gui.drawTile(3, 14, Tile.IGNIS, Colors.IGNIS, Colors.BG_UI);
        }
}


