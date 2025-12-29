package com.Nunes.aqua_and_ignis;

import com.Nunes.aqua_and_ignis.model.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
}