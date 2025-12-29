package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Monster;
import view.Theme.Colors;

import static view.Scale.SCALE;


public class SlimeView {
    public void draw(Monster monster, GUI gui) {
        Position position = monster.getPosition();

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.SLIME);
    }
}
