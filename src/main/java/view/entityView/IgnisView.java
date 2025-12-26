package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Player;
import view.Colors;

import static view.Scale.SCALE;


public class IgnisView {
    public void draw(Player player, GUI gui) {
        Position position = player.getPosition();
        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.IGNIS);
    }
}