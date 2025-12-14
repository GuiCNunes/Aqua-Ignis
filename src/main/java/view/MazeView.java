package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Maze;
import model.Position;
import model.entity.Monster;
import org.w3c.dom.Text;
import view.entityView.MonsterView;
import view.entityView.PlayerView;

public class MazeView extends View<Maze>{

    private static int visionRadius = 3;

    private PlayerView playerView = new PlayerView();
    private MonsterView monsterView = new MonsterView();

    public MazeView(Maze maze) {
        super(maze);
    }

    @Override
    public void drawElements(GUI gui) {
        Maze maze = getModel();

        drawMaze(maze, gui);

        playerView.draw(maze.getAqua(), gui);
        playerView.draw(maze.getIgnis(), gui);

    }
    private void drawMaze(Maze maze, GUI gui) {
        drawDarkness(maze, gui);

        Position aquaPos = maze.getAqua().getPosition();
        drawVision(maze, gui, aquaPos);

        Position ignisPos = maze.getIgnis().getPosition();
        drawVision(maze, gui, ignisPos);

        for(Monster monster  : maze.getMonsters()){
            Position monsterPos = monster.getPosition();
            int x = monsterPos.getX();
            int y = monsterPos.getY();

            if(isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)){
                monsterView.draw(monster, gui);
            }
        }
    }

    private void drawDarkness(Maze maze, GUI gui) {
        gui.fillRect(0, 0, maze.getWidth(), maze.getHeight(),
                TextColor.Factory.fromString("#000000"));
    }

    private void drawVision(Maze maze, GUI gui, Position position) {
        for(int y = 0; y < maze.getHeight(); y++){
            for(int x = 0; x < maze.getWidth(); x++){
                if(isVisible(position, x, y)){
                    if(maze.isWall(x, y)){
                        gui.drawPixel(x, y, TextColor.Factory.fromString("#4A3B2D"));
                    }
                    else{
                        gui.drawPixel(x, y, TextColor.Factory.fromString("#765341"));                    }
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