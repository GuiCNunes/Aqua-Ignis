package com.Nunes.aqua_and_ignis.controller.menu;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.states.MenuState;

import java.io.IOException;

public class GameOverController extends Controller<Menu> {
    public GameOverController(Menu menu) {
        super (menu);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if(op == GUI.operation.select){
            game.setState(new MenuState(new Menu()));
        }
    }
}
