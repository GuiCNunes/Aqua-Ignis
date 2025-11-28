package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.entity.Player;

public class AquaView {
    public void draw(Player player, TextGraphics g) {
        Position position = player.getPosition();
        g.setForegroundColor(TextColor.Factory.fromString("#00BFFF"));
        g.putString(new TerminalPosition(position.getX(), position.getY()), "O");
    }
}
