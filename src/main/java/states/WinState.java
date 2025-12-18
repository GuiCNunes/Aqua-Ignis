package states;

import controller.Controller;
import controller.menu.MenuController;
import controller.menu.WinController;
import model.menu.Menu;
import view.View;
import view.WinView;

public class WinState extends State<Menu>{
    public WinState(Menu menu){
        super(menu);
    }
    @Override
    public View<Menu> getView(){return new WinView(getModel());}

    @Override
    public Controller<Menu> getController(){return new WinController(getModel());}
}
