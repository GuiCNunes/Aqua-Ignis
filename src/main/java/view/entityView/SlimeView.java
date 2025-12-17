package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Monster;
import static view.Scale.SCALE;


public class SlimeView {
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();

        TextColor slimeColor =  TextColor.Factory.fromString("#00FF00");

        gui.drawString(position.getX() *SCALE, position.getY()* SCALE, "0",
                slimeColor, slimeColor);
    }
}
