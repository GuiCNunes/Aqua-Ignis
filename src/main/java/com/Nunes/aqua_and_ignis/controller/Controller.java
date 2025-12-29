package com.Nunes.aqua_and_ignis.controller;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;

import java.io.IOException;

public abstract class Controller<T> {

    private T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void command(Game game, GUI.operation operation, long time) throws IOException;
}
