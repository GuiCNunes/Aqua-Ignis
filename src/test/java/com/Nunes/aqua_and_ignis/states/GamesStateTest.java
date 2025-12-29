package com.Nunes.aqua_and_ignis.states;

import static org.junit.jupiter.api.Assertions.*;

import com.Nunes.aqua_and_ignis.states.GameState;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.model.Maze;

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
