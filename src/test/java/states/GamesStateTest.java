package states;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Maze;

class GamesStateTest {

    @Test
    void CreateStateTest(){
        Maze maze = new Maze(50,50);
        GameState gameState = new GameState(maze);
        assertNotNull(gameState);
    }

    @Test
    void GetModelTest(){
        Maze maze = new Maze(50,50);
        GameState gameState = new GameState(maze);
        assertEquals(maze, gameState.getModel());
    }

    @Test
    void GetViewTest(){
        Maze maze = new Maze(50,50);
        GameState gameState = new GameState(maze);
        assertNotNull(gameState.getView());
    }

    @Test
    void GetControllerTest(){
        Maze maze = new Maze(50,50);
        GameState gameState = new GameState(maze);
        assertNotNull(gameState.getController());
    }
}
