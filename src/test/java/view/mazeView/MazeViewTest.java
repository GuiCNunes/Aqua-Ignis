package view.mazeView;

import gui.GUI;
import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class MazeViewTest {
    private Maze maze;
    private GUI gui;
    private MazeView mazeView;
    private MazeWorldRenderer worldRenderer;
    private HudRenderer hudRenderer;

    @BeforeEach
    void setUp() throws Exception {
        maze = mock(Maze.class);
        gui = mock(GUI.class);
        mazeView = new MazeView(maze);

        worldRenderer = mock(MazeWorldRenderer.class);
        hudRenderer = mock(HudRenderer.class);

        Field worldField = MazeView.class.getDeclaredField("worldRenderer");
        worldField.setAccessible(true);
        worldField.set(mazeView, worldRenderer);

        Field hudField = MazeView.class.getDeclaredField("hudRenderer");
        hudField.setAccessible(true);
        hudField.set(mazeView, hudRenderer);
    }

    @Test
    void drawElements() {
        mazeView.drawElements(gui);

        verify(worldRenderer).render(maze, gui);
        verify(hudRenderer).render(maze, gui);
    }
}