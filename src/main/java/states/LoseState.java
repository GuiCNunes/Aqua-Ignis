package states;

import controller.Controller;
import controller.menu.GameOverController;
import model.menu.Menu;
import view.menuView.LoseView;
import view.View;

public class LoseState extends State<Menu>{
    public LoseState(Menu menu){
        super(menu);
    }
    @Override
    public View<Menu> getView(){return new LoseView(getModel());}

    @Override
    public Controller<Menu> getController(){return new GameOverController(getModel());}
}
