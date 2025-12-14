package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Monster;

public class GhostView {
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();
        gui.drawString(position.getX(), position.getY(), "∫",
                TextColor.Factory.fromString("#FFFFFF"), null);
    }
}