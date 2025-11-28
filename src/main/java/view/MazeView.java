package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Maze;
import model.entity.Monster;

public class MazeView {
    private PlayerView playerView = new PlayerView();
    private MonsterView monsterView = new MonsterView();

    public void draw(Maze maze, TextGraphics g) {
        drawMaze(maze, g);
        playerView.draw(maze.getAqua(), g); //'~', "#00BFFF");
        playerView.draw(maze.getIgnis(), g); //'^', "#FFA500");

        for (Monster monster : maze.getMonsters()){
            monsterView.draw(monster, g, 'M', "#00FF00");
        }
    }
    private void drawMaze(Maze maze, TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#765341"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(maze.getWidth(), maze.getHeight()), ' ');
    }
}