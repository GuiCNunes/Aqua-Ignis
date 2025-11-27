package view;


import com.googlecode.lanterna.graphics.TextGraphics;
import model.Maze;

public class MazeView {
    private PlayerView playerView = new PlayerView();

    public void draw(Maze maze, TextGraphics g) {
        playerView.draw(maze.getAqua(), g, '~', "#00BFFF");
        playerView.draw(maze.getIgnis(), g, '^', "#FFA500");
    }
}
