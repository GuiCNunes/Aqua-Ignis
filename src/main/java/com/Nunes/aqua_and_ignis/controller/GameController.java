package com.Nunes.aqua_and_ignis.controller;

import com.Nunes.aqua_and_ignis.model.Maze;

public abstract class GameController extends Controller<Maze> {
    public GameController(Maze maze) {
        super(maze);
    }
}
