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
            initializeGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeGUI() throws IOException {
        int w = 90 * Scale.SCALE + 2;
        int h = 30 * Scale.SCALE + 2;
        this.gui = new LanternaGUI(w, h);
        this.gui.startScreen();
    }

    public void recreateGUI() throws IOException {
        int newScale = Scale.SCALE + 1;
        if (newScale > 3) newScale = 1;
        Scale.setScale(newScale);
        if (this.gui != null) this.gui.stopScreen();
        initializeGUI();
    }

    public void setState(State state) { this.state = state; }

    public void run() throws IOException {
        int frameTime = 1000 / 30;
        while (this.state != null) {
            long start = System.currentTimeMillis();
            state.command(this, gui, start);
            long sleep = frameTime - (System.currentTimeMillis() - start);
            try { if (sleep > 0) Thread.sleep(sleep); } catch (InterruptedException ignored) {}
        }
        gui.stopScreen();
    }
}