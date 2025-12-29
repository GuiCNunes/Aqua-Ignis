package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.controller.Controller;
import com.Nunes.aqua_and_ignis.controller.MazeController;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.view.mazeView.MazeView;
import com.Nunes.aqua_and_ignis.view.View;

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
