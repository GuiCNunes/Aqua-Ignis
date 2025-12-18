package view;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Maze;
import model.Position;
import model.entity.Key;
import model.entity.Monster;
import model.entity.Star;
import view.entityView.KeyView;
import view.entityView.MonsterView;
import view.entityView.PlayerView;
import view.entityView.StarView;

import static view.Scale.SCALE;

public class MazeView extends View<Maze> {

    private static int visionRadius = 20;

    private final PlayerView playerView = new PlayerView();
    private final MonsterView monsterView = new MonsterView();
    private final StarView starView = new StarView();
    private final KeyView keyView = new KeyView();

    public MazeView(Maze maze) {
        super(maze);
    }

    private int sx(int x) { return x * SCALE; }
    private int sy(int y) { return y * SCALE; }

    @Override
    public void drawElements(GUI gui) {
        Maze maze = getModel();

        drawMaze(maze, gui);

        drawStarCounter(maze, gui);
        drawExitStatus(maze, gui);
        drawTimer(maze, gui);

        playerView.draw(maze.getAqua(), gui);
        playerView.draw(maze.getIgnis(), gui);
    }

    private void drawMaze(Maze maze, GUI gui) {
        drawDarkness(maze, gui);
        drawMemory(maze, gui);

        Position aquaPos = maze.getAqua().getPosition();
        drawVision(maze, gui, aquaPos);

        Position ignisPos = maze.getIgnis().getPosition();
        drawVision(maze, gui, ignisPos);

        drawStars(maze, gui, aquaPos, ignisPos);
        drawKey(maze, gui, aquaPos, ignisPos);

        for (Monster monster : maze.getMonsters()) {
            Position mp = monster.getPosition();
            int x = mp.getX();
            int y = mp.getY();

            if (isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)) {
                monsterView.draw(monster, gui);
            }
        }
    }

    private void drawStars(Maze maze, GUI gui, Position aquaPos, Position ignisPos) {
        for(Star star: maze.getStars()){
            int x = star.getPosition().getX();
            int y = star.getPosition().getY();

            if(isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)) {
                starView.draw(gui, star);
            }
        }
    }

    private void drawDarkness(Maze maze, GUI gui) {
        gui.fillRect(0, 0, maze.getWidth() * SCALE, maze.getHeight() * SCALE,
                TextColor.Factory.fromString("#000000"));
    }

    private void drawVision(Maze maze, GUI gui, Position position) {

        TextColor openExitColor = TextColor.Factory.fromString("#FFFFFF");

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (!isVisible(position, x, y)) continue;

                maze.updateMemory(x,y);

                TextColor c;
                Position exitPosition = maze.getExit();

                if(x == exitPosition.getX() && y == exitPosition.getY() && maze.isExitOpen()){
                    c = openExitColor;
                }
                else{
                    c = maze.isWall(x, y)
                            ? TextColor.Factory.fromString("#4A3B2D")
                            : TextColor.Factory.fromString("#765341");
                }
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

    private void drawMemory(Maze maze, GUI gui) {
        TextColor shadow = TextColor.Factory.fromString("#282828");

        for(int y = 0; y < maze.getHeight(); y++) {
            for(int x = 0; x < maze.getWidth(); x++) {
                if(maze.isMemorized(x,y)){
                    Position aquaPos = maze.getAqua().getPosition();
                    Position ignisPos = maze.getIgnis().getPosition();

                    if((!isVisible(aquaPos, x, y) && !isVisible(ignisPos, x, y))) {
                        gui.fillRect(sx(x), sy(y), SCALE, SCALE, shadow);
                    }
                }
            }
        }
    }

    private void drawStarCounter(Maze maze, GUI gui){
        int collected = maze.getCollectedStars();
        int maxStars = 3;

        StringBuilder starDisplay = new StringBuilder("Stars: ");

        for(int i = 0; i < collected; i++){
            starDisplay.append("*");
        }

        for(int i = collected; i < maxStars; i++){
            starDisplay.append("-");
        }

        TextColor starColor = TextColor.Factory.fromString("#FFD700");

        gui.drawText(3, 1, starDisplay.toString(), starColor, TextColor.Factory.fromString("#2C2C2C"));
    }

    private void drawKey(Maze maze, GUI gui, Position aquaPos, Position ignisPos) {
        Key key = maze.getKey();

        if (key == null) return;

        int x = key.getPosition().getX();
        int y = key.getPosition().getY();

        if(isVisible(aquaPos, x, y) || isVisible(ignisPos, x, y)) {
            keyView.draw(gui, key);
        }
    }

    private void drawExitStatus(Maze maze, GUI gui){
        String status;
        TextColor statusColor;

        if(maze.isExitOpen()){
            status = "Exit : OPEN";
            statusColor = TextColor.Factory.fromString("#00FF00");
        }
        else{
            status = "Exit : CLOSED";
            statusColor = TextColor.Factory.fromString("#FF0000");
        }

        int x = 20;
        int y = 1;

        TextColor bgColor = TextColor.Factory.fromString("#2C2C2C");
        gui.drawText(x, y, status,  statusColor, bgColor);
    }

    private void drawTimer(Maze maze, GUI gui){
        long seconds = maze.getTime();
        String time = String.format("Time: %02d:%02d", seconds / 60, seconds % 60);

        gui.drawText(40, 1, time, TextColor.Factory.fromString("#FFFFFF"),
                TextColor.Factory.fromString("#2C2C2C"));
    }

}
