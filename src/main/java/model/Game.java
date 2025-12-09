package model;

import controller.MazeController;
import model.menu.Menu;
import states.MenuState;
import states.State;
import view.MazeView;
import gui.GUI;
import gui.LanternaGUI;


import java.io.IOException;

public class Game {

    private GUI gui;
    private State state;

    public Game() {

        try {
            this.state = new MenuState(new Menu());
            this.gui = new LanternaGUI(90,30);

            gui.startScreen();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setState(State state) {
        this.state = state;
    }


    public void run() throws IOException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.command(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }

        gui.stopScreen();
    }

}