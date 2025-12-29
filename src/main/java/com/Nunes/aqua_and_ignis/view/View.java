package com.Nunes.aqua_and_ignis.view;

import com.Nunes.aqua_and_ignis.gui.GUI;
import java.io.IOException;

public abstract class View<T> {
    private T model;

    public View(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui) throws IOException;
}
