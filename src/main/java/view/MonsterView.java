package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Monster;
import model.Position;

public class MonsterView {
    public void draw(Monster monster, TextGraphics g, char character, String color) {
        Position position = monster.getPosition();
        g.setForegroundColor(TextColor.Factory.fromString(color));
        g.putString(new TerminalPosition(position.getX(), position.getY()), String.valueOf(character));
    }
}


