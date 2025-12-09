package states;


import controller.Controller;
import gui.GUI;
import model.Game;
import view.View;

import java.io.IOException;

public abstract class State<T> {

    private T model;
    private View view;
    private Controller controller;

    public State(T model) {
        this.model = model;
        this.view = getView();
        this.controller = getController();
    }

    public abstract View<T> getView();

    public abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void command(Game game, GUI gui, long time) throws IOException {
        GUI.operation action = gui.getOperation();
        controller.command(game, action, time);
        view.draw(gui);
        gui.refresh();
    }
}
