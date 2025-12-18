package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Player;
import static view.Scale.SCALE;


public class IgnisView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();

        TextColor ignisColor = TextColor.Factory.fromString("#FFA500");

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, ignisColor);
    }
}