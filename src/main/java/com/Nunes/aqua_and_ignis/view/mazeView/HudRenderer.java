package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

public class HudRenderer {

    public void render(Maze maze, GUI gui) {
        drawBackground(gui);
        drawStarCounter(maze, gui);
        drawExitStatus(maze, gui);
        drawTimer(maze, gui);
    }

    private void drawBackground(GUI gui) {
        gui.fillRect(0, 0, gui.getWidth(), 2, Colors.BG_UI);
    }

    private void drawStarCounter(Maze maze, GUI gui) {
        int collected = maze.getCollectedStars();
        int maxStars = 3;

        StringBuilder starDisplay = new StringBuilder("Stars: ");
        for (int i = 0; i < collected; i++) starDisplay.append("*");
        for (int i = collected; i < maxStars; i++) starDisplay.append("-");

        gui.drawText(3, 1, starDisplay.toString(), Colors.STAR, Colors.BG_UI);
    }

    private void drawExitStatus(Maze maze, GUI gui) {
        String status;
        Color statusColor;

        if (maze.isExitOpen()) {
            status = "Exit : OPEN";
            statusColor = Colors.SLIME;
        } else {
            status = "Exit : CLOSED";
            statusColor = Colors.KEY;
        }

        gui.drawText(20, 1, status, statusColor, Colors.BG_UI);
    }

    private void drawTimer(Maze maze, GUI gui) {
        long seconds = maze.getTime();
        String time = String.format("Time: %02d:%02d", seconds / 60, seconds % 60);

        gui.drawText(40, 1, time, Colors.WHITE, Colors.BG_UI);
    }
}
