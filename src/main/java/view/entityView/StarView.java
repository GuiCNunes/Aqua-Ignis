package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Star;
import view.Theme.Colors;
import view.View;

import static view.Scale.SCALE;

public class StarView extends View<Star> {
    public StarView() {
        super(null);
    }
    @Override
    public void drawElements(GUI gui) {}

    public void draw(GUI gui, Star star) {

        Position position = star.getPosition();

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.STAR);
    }
}
