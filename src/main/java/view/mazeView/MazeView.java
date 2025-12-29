package view.mazeView;

import gui.GUI;
import model.Maze;
import view.View;
import view.entityView.KeyView;
import view.entityView.MonsterView;
import view.entityView.PlayerView;
import view.entityView.StarView;

public class MazeView extends View<Maze> {

    private final MazeWorldRenderer worldRenderer;
    private final HudRenderer hudRenderer;

    public MazeView(Maze maze) {
        super(maze);

        FieldOfView fov = new FieldOfView(20);

        this.worldRenderer = new MazeWorldRenderer(
                fov,
                new PlayerView(),
                new MonsterView(),
                new StarView(),
                new KeyView()
        );

        this.hudRenderer = new HudRenderer();
    }

    @Override
    public void drawElements(GUI gui) {
        Maze maze = getModel();
        worldRenderer.render(maze, gui);
        hudRenderer.render(maze, gui);
    }
}
