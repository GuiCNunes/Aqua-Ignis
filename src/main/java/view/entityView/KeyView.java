package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.entity.Key;
import model.entity.Star;
import view.View;

import static view.Scale.SCALE;

public class KeyView extends View<Key> {

    public KeyView(Key key) {
        super(null);
    }
    @Override
    public void drawElements(GUI gui) {}

    public void draw(GUI gui, Star star) {
        TextColor keyColor = TextColor.Factory.fromString("#9A7B4F");

        gui.drawString(star.getPosition().getX() * SCALE, star.getPosition().getY() * SCALE,
                "K" ,keyColor, keyColor);
    }
}
