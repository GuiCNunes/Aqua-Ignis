package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Position;
import model.entity.Key;
import model.entity.Star;
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
        TextColor keyColor = TextColor.Factory.fromString("#FF0000");

        gui.fillRect(position.getX() * SCALE, position.getY() * SCALE, SCALE, SCALE, keyColor);
    }
}
