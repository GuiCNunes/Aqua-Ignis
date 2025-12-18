package controller.menu;

import controller.Controller;
import controller.GameController;
import gui.GUI;
import model.Game;
import model.menu.Menu;
import states.MenuState;

import java.io.IOException;

public class WinController extends Controller<Menu> {
    public WinController(Menu menu) {
        super (menu);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        if(op == GUI.operation.select){
            game.setState(new MenuState(new Menu()));
        }
    }
}
