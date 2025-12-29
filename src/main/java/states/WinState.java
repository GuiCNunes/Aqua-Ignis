package states;

import controller.Controller;
import controller.menu.WinController;
import model.menu.Menu;
import view.View;
import view.menuView.WinView;

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
