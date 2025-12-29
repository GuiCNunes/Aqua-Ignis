package view.entityView;

import model.Position;
import gui.GUI;
import model.entity.Player;
import view.Theme.Colors;

import static view.Scale.SCALE;

public class AquaView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.AQUA);
    }
}
