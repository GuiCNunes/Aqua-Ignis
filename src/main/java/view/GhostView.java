package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.entity.Monster;

public class GhostView {
    public void draw(Monster monster, TextGraphics g) {
        Position position = monster.getPosition();
        g.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        g.putString(new TerminalPosition(position.getX(), position.getY()), "∫");
    }
}
