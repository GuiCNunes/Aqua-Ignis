package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.controller.menu.WinController;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.View;
import com.Nunes.aqua_and_ignis.view.menuView.WinView;

public class WinState extends State<Menu>{

        private final long elapsedTime;
        private final int stars;

    public WinState(Menu menu, int stars, long elapsedTime){
        super(menu);
        this.elapsedTime = elapsedTime;
        this.stars = stars;
    }
    @Override
    public View<Menu> getView(){return new WinView(getModel(), stars, elapsedTime);}

    @Override
    public Controller<Menu> getController(){return new WinController(getModel());}
}
