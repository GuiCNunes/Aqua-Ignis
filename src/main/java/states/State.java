package states;


import view.View;

public abstract class State<T> {

    private T model;
    private View view;
    public State(T model) {
        this.model = model;
        this.view = getView();
    }

    protected abstract View<T> getView();

    public T getModel() {
        return model;
    }
}
