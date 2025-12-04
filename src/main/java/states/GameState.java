package states;

import model.Maze;
import view.MazeView;
import view.View;

public class GameState extends State<Maze> {

    public GameState(Maze maze) {
        super(maze);
    }

    @Override
    protected View<Maze> getView() {
        return new MazeView(getModel());
    }
}
