package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.entity.Monster;


public class SlimeView {
    public void draw(Monster monster, TextGraphics g) {
        Position position = monster.getPosition();
        g.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        g.putString(new TerminalPosition(position.getX(), position.getY()), "0");
    }
}
