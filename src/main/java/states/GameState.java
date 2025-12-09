package states;

import controller.Controller;
import controller.MazeController;
import model.Maze;
import view.MazeView;
import view.View;

public class GameState extends State<Maze> {

    public GameState(Maze maze) {
        super(maze);
    }

    @Override
    public View<Maze> getView() {
        return new MazeView(getModel());
    }

    @Override
    public Controller<Maze> getController() {
        return new MazeController(getModel());
    }
}
