package controller.menu;

import controller.Controller;
import gui.GUI;
import model.Game;
import model.Maze;
import model.menu.Menu;
import states.GameState;
import states.HelpState;
import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        switch (op) {
            case up: getModel().previousEntry(); break;
            case down: getModel().nextEntry(); break;
            case select:
                if (getModel().isSelectedExit()) game.setState(null);
                else if (getModel().isSelectedStart()) game.setState(new GameState(new Maze(90, 30)));
                else if (getModel().isSelectedSize()) game.recreateGUI();
                else if (getModel().isSelectedHelp()) game.setState(new HelpState(getModel()));
                break;
            case quit: game.setState(null); break;
        }
    }
}