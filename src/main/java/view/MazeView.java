package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Maze;
import model.Position;
import model.entity.Monster;
import view.entityView.MonsterView;
import view.entityView.PlayerView;

import static view.Scale.SCALE;

public class MazeView extends View<Maze> {

    private static int visionRadius = 20;

    private final PlayerView playerView = new PlayerView();
    private final MonsterView monsterView = new MonsterView();

    public MazeView(Maze maze) {
        super(maze);
    }

    private int sx(int x) { return x * SCALE; }
    private int sy(int y) { return y * SCALE; }

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

        for (Monster monster : maze.getMonsters()) {
            Position mp = monster.getPosition();
            int x = mp.getX();
            int y = mp.getY();

            if (isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)) {
                monsterView.draw(monster, gui);
            }
        }
    }

    private void drawDarkness(Maze maze, GUI gui) {
        gui.fillRect(0, 0, maze.getWidth() * SCALE, maze.getHeight() * SCALE,
                TextColor.Factory.fromString("#000000"));
    }

    private void drawVision(Maze maze, GUI gui, Position position) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (!isVisible(position, x, y)) continue;

                TextColor c = maze.isWall(x, y)
                        ? TextColor.Factory.fromString("#4A3B2D")
                        : TextColor.Factory.fromString("#765341");

                gui.fillRect(sx(x), sy(y), SCALE, SCALE, c);
            }
        }
    }

    private boolean isVisible(Position position, int x, int y) {
        int dx = position.getX() - x;
        int dy = position.getY() - y;

        if (dx * dx + dy * dy > visionRadius * visionRadius) return false;

        int x0 = position.getX();
        int y0 = position.getY();

        int absDx = Math.abs(x - x0);
        int absDy = Math.abs(y - y0);

        int stepX = (x0 < x) ? 1 : -1;
        int stepY = (y0 < y) ? 1 : -1;

        int dif = absDx - absDy;
        int currentX = x0;
        int currentY = y0;

        while (currentX != x || currentY != y) {

            if (getModel().isWall(currentX, currentY) && (currentX != x0 || currentY != y0)) {
                return false;
            }

            int dif2 = 2 * dif;

            if (dif2 > -absDy) {
                dif -= absDy;
                currentX += stepX;
            }

            if (dif2 < absDx) {
                dif += absDx;
                currentY += stepY;
            }
        }
        return true;
    }
}
