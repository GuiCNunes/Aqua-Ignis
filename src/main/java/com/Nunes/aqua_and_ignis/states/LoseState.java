package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.controller.menu.GameOverController;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.view.menuView.LoseView;
import com.Nunes.aqua_and_ignis.view.View;

public class LoseState extends State<Menu>{
    public LoseState(Menu menu){
        super(menu);
    }
    @Override
    public View<Menu> getView(){return new LoseView(getModel());}

    @Override
    public Controller<Menu> getController(){return new GameOverController(getModel());}
}
