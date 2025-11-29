package view.entityView;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.entity.Monster;
import model.entity.Slime;

public class MonsterView {
    private SlimeView slimeView = new SlimeView();
    private GhostView ghostView = new GhostView();

    public void draw(Monster monster, TextGraphics g) {
        if (monster instanceof Slime) {
            slimeView.draw(monster, g);
        } else {
            ghostView.draw(monster, g);
        }
    }
}


