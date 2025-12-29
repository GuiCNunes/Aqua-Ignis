package view.entityView;

import gui.GUI;
import model.Position;
import model.entity.Key;
import view.Theme.Colors;
import view.View;

import static view.Scale.SCALE;

public class KeyView extends View<Key> {

    public KeyView() {
        super(null);
    }
    @Override
    public void drawElements(GUI gui) {}

    public void draw(GUI gui, Key key) {

        Position position = key.getPosition();

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, Colors.KEY);
    }
}
