package controller.menu;

import controller.Controller;
import gui.GUI;
import model.Game;
import model.Maze;
import model.menu.Menu;
import states.GameState;
import view.Scale;

import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void command(Game game, GUI.operation operation, long time) throws IOException {

        switch (operation) {
            case up : getModel().previousEntry();
                break;
            case down : getModel().nextEntry();
                break;
            case select:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedStart()) game.setState(new GameState(new Maze(90,30)));
                if (getModel().isSelectedSize()) {
                    int currentScale = Scale.SCALE;
                    int newScale = currentScale + 1;
                    if (newScale > 3) newScale = 1;
                    Scale.setScale(newScale);
                    game.recreateGUI();
                }
                break;
            case quit:
                game.setState(null);
                break;
        }

    }
}