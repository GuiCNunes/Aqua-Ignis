package view.entityView;

import com.googlecode.lanterna.TextColor;
import model.Position;
import gui.GUI;
import model.entity.Player;
import static view.Scale.SCALE;

public class AquaView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();

        TextColor aquaColor = TextColor.Factory.fromString("#00BFFF");

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, aquaColor);
    }
}
