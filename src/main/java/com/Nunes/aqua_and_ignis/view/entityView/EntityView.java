package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;


public interface EntityView<T> {
    void draw(T entity, GUI gui);
}
