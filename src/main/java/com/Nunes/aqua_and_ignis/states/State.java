package com.Nunes.aqua_and_ignis.states;


import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.view.View;

import java.io.IOException;

public abstract class State<T> {

    private T model;
    private View<T> view;
    private Controller<T> controller;

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
        GUI.operation operation = gui.getOperation();
        controller.command(game, operation, time);
        view.draw(gui);
    }
}
