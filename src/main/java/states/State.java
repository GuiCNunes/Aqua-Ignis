package states;


import controller.Controller;
import view.View;

public abstract class State<T> {

    private T model;
    private View view;
    private Controller controller;

    public State(T model) {
        this.model = model;
        this.view = getView();
        this.controller = getController();
    }

    protected abstract View<T> getView();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }
}
