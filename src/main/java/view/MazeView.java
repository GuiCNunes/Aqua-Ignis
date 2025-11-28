package view;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Maze;
import model.entity.Monster;

public class MazeView {
    private PlayerView playerView = new PlayerView();
    private MonsterView monsterView = new MonsterView();

    public void draw(Maze maze, TextGraphics g) {
        playerView.draw(maze.getAqua(), g); //'~', "#00BFFF");
        playerView.draw(maze.getIgnis(), g); //'^', "#FFA500");

        for (Monster monster : maze.getMonsters()){
            monsterView.draw(monster, g, 'M', "#00FF00");
        }
    }
}
