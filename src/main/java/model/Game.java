package model;

import controller.MazeController;
import view.MazeView;
import gui.GUI;
import gui.LanternaGUI;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Game {

    private Maze maze;
    private MazeView mazeView;
    private MazeController mazeController;
    private GUI gui;

    public Game() {
        try {
            this.gui = new LanternaGUI(90,30);

            this.maze = new Maze(gui.getWidth(), gui.getHeight());
            this.mazeView = new MazeView(maze);
            this.mazeController = new MazeController(maze);

            gui.startScreen();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {

        gui.clear();

        mazeView.draw(gui);

        gui.refresh();

    }

    public GUI getGui() {
        return gui;
    }



    public void run() throws IOException {

        draw();

        while (true) {

            GUI.operation op = gui.getOperation();

            if(op == GUI.operation.quit){
                gui.stopScreen();
                break;
            }

            if(op != GUI.operation.none) mazeController.command(this, op);

            draw();

            if (maze.checkWin(maze.getAqua().getPosition()) && maze.checkWin(maze.getIgnis().getPosition())) {
                System.out.println("Congratulations! You win!");
                gui.stopScreen();
                break;
            }

            if(maze.checkLoss()){
                System.out.println("Game Over my friend(s)");
                gui.stopScreen();
                break;
            }
        }
    }

}