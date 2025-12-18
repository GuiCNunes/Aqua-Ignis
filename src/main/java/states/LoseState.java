package states;

import controller.Controller;
import controller.menu.GameOverController;
import controller.menu.MenuController;
import controller.menu.WinController;
import model.menu.Menu;
import view.LoseView;
import view.View;
import view.WinView;

public class LoseState extends State<Menu>{
    public LoseState(Menu menu){
        super(menu);
    }
    @Override
    public View<Menu> getView(){return new LoseView(getModel());}

    @Override
    public Controller<Menu> getController(){return new GameOverController(getModel());}
}
