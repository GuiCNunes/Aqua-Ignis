package states;

import controller.Controller;
import controller.menu.GameOverController;
import model.menu.Menu;
import view.View;
import view.menuView.HelpView;

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
        return new GameOverController(getModel());
    }
}