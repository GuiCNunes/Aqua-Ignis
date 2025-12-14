package view.entityView;

import com.googlecode.lanterna.TextColor;
import model.Position;
import gui.GUI;
import model.entity.Player;

public class AquaView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();
        gui.drawString(position.getX(), position.getY(), "0",
                TextColor.Factory.fromString("#00BFFF"), null);
    }
}
