package view.entityView;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.entity.Star;
import view.View;

import static view.Scale.SCALE;

public class StarView extends View<Star> {
    public StarView() {
        super(null);
    }
    @Override
    public void drawElements(GUI gui) {}

    public void draw(GUI gui, Star star) {
        TextColor starColor = TextColor.Factory.fromString("#FFD700");

        gui.drawString(star.getPosition().getX() * SCALE, star.getPosition().getY() * SCALE,
                "*" ,starColor, null);
    }
}
