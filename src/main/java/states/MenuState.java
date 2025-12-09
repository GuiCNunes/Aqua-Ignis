package states;

import controller.Controller;
import controller.menu.MenuController;
import model.menu.Menu;
import view.View;
import view.menuView.MenuView;

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
