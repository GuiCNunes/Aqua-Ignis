package controller;

import gui.GUI;
import model.Game;

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
