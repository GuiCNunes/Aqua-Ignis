package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Slime;

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


