package com.Nunes.aqua_and_ignis.controller.entity;

import com.Nunes.aqua_and_ignis.controller.GameController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;

import java.io.IOException;

public class PlayerController extends GameController {

    private IgnisController ignisController;
    private AquaController aquaController;

    public PlayerController(Maze maze) {
        super(maze);
        this.ignisController = new IgnisController(maze);
        this.aquaController = new AquaController(maze);
    }

    @Override
    public void command(Game game, GUI.operation op, long time) throws IOException {
        switch (op) {
            case up:
            case down:
            case left:
            case right:
                ignisController.command(game, op, time);
                break;
            case w:
            case s:
            case a:
            case d:
                aquaController.command(game, op, time);
                break;
            default: break;
        }
    }
}