package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Maze;
import model.Position;
import model.entity.Monster;

public class MazeView {

    private static int visionRadius = 3;

    private PlayerView playerView = new PlayerView();
    private MonsterView monsterView = new MonsterView();

    public void draw(Maze maze, TextGraphics g) {
        drawMaze(maze, g);
        playerView.draw(maze.getAqua(), g); //'~', "#00BFFF");
        playerView.draw(maze.getIgnis(), g); //'^', "#FFA500");

    }
    private void drawMaze(Maze maze, TextGraphics g) {
        drawDarkness(maze, g);

        Position aquaPos = maze.getAqua().getPosition();
        drawVision(maze, g, aquaPos);

        Position ignisPos = maze.getIgnis().getPosition();
        drawVision(maze, g, ignisPos);

        for(Monster monster  : maze.getMonsters()){
            Position monsterPos = monster.getPosition();
            int x = monsterPos.getX();
            int y = monsterPos.getY();

            if(isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)){
                monsterView.draw(monster, g);
            }
        }
    }

    private void drawDarkness(Maze maze, TextGraphics g) {
        g.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        g.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(maze.getWidth(), maze.getHeight()), ' ');
    }

    private void drawVision(Maze maze, TextGraphics g, Position position) {
        for(int y = 0; y < maze.getHeight(); y++){
            for(int x = 0; x < maze.getWidth(); x++){
                if(isVisible(position, x, y)){
                    g.setBackgroundColor(TextColor.Factory.fromString("#765341"));
                    g.setCharacter(x,y, ' ');
                }
            }
        }
    }

    private boolean isVisible(Position position, int x, int y) {
        int dx = position.getX() - x;
        int dy = position.getY() - y;
        return dx * dx + dy * dy <= visionRadius * visionRadius;
    }


}