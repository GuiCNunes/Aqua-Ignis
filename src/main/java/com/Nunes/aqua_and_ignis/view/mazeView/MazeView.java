package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.view.View;
import com.Nunes.aqua_and_ignis.view.entityView.KeyView;
import com.Nunes.aqua_and_ignis.view.entityView.MonsterView;
import com.Nunes.aqua_and_ignis.view.entityView.PlayerView;
import com.Nunes.aqua_and_ignis.view.entityView.StarView;

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
