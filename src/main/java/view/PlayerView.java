package view;

import model.entity.Player;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import model.Position;

public class PlayerView {
    public void draw(Player player, TextGraphics g, char character, String color) {
        Position position = player.getPosition();
        g.setForegroundColor(TextColor.Factory.fromString(color));
        g.putString(new TerminalPosition(position.getX(), position.getY()), String.valueOf(character));
    }
}
