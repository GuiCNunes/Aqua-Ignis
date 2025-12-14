package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Player;

public class IgnisView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();
        gui.drawString(position.getX(), position.getY(), "X",
                TextColor.Factory.fromString("#FFA500"), null);
    }
}