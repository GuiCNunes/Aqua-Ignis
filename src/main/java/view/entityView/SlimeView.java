package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Monster;


public class SlimeView {
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();
        gui.drawString(position.getX(), position.getY(), "0",
                TextColor.Factory.fromString("00FF00"), null);
    }
}
