package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Slime;

public class MonsterView {
    private SlimeView slimeView = new SlimeView();
    private GhostView ghostView = new GhostView();

    public void draw(Monster monster, GUI gui, Color background, int frame) {
        if (monster instanceof Slime) {
            slimeView.draw(monster, gui, background, frame);
        } else {
            ghostView.draw(monster, gui, background, frame);
        }
    }
}


