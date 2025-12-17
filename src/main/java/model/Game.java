package model;

import model.menu.Menu;
import states.MenuState;
import states.State;
import gui.GUI;
import gui.LanternaGUI;
import view.Scale;

import java.io.IOException;

public class Game {

    private GUI gui;
    private State state;

    public Game() {

        try {
            this.state = new MenuState(new Menu());
            int w = 90 * Scale.SCALE + 2;
            int h = 30 * Scale.SCALE + 2;
            this.gui = new LanternaGUI(w, h);
            gui.startScreen();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recreateGUI() throws IOException {
        if(this.gui != null) this.gui.stopScreen();
        int w = 90 * Scale.SCALE + 2;
        int h = 30 * Scale.SCALE + 2;
        this.gui = new LanternaGUI(w, h);
        this.gui.startScreen();
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