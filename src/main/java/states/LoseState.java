package states;

import controller.Controller;
import controller.menu.MenuController;
import model.menu.Menu;
import view.View;
import view.WinView;

public class LoseState extends State<Menu>{
    public LoseState(Menu menu){
        super(menu);
    }
    @Override
    public View<Menu> getView(){return new WinView(getModel());}

    @Override
    public Controller<Menu> getController(){return new MenuController(getModel());}
}
