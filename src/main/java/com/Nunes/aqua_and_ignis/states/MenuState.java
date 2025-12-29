package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.controller.menu.MenuController;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.View;
import com.Nunes.aqua_and_ignis.view.menuView.MenuView;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    public View<Menu> getView() {
        return new MenuView(getModel());
    }

    @Override
    public Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
