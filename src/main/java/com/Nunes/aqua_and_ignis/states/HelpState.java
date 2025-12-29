package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.controller.menu.HelpController;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.View;
import com.Nunes.aqua_and_ignis.view.menuView.HelpView;

public class HelpState extends State<Menu> {
    public HelpState(Menu model) {
        super(model);
    }
    @Override
    public View<Menu> getView() {
        return new HelpView(getModel());
    }

    @Override
    public Controller<Menu> getController() {
        return new HelpController(getModel());
    }
}