package view.entityView;

import gui.GUI;
import model.entity.Monster;
import model.entity.Slime;

public class MonsterView {
    private SlimeView slimeView = new SlimeView();
    private GhostView ghostView = new GhostView();

    public void draw(Monster monster, GUI gui) {
        if (monster instanceof Slime) {
            slimeView.draw(monster, gui);
        } else {
            ghostView.draw(monster, gui);
        }
    }
}


