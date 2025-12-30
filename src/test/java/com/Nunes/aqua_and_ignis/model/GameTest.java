package com.Nunes.aqua_and_ignis.model;

import com.Nunes.aqua_and_ignis.states.MenuState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;

public class GameTest {
    @BeforeEach
    void setUp() {
        com.Nunes.aqua_and_ignis.view.Scale.setScale(1);
    }

    @Test
    void testConstructorAndSetState() {
        try {
            Game game = new Game();
            assertNotNull(game);

            game.setState(null);
            game.setState(new MenuState(new Menu()));

        } catch (Exception e) {
        }
    }

    @Test
    void TestCreateGame(){
        Game game = new Game();
        assertNotNull(game);
    }
    @Test
    void TestSetStateNull(){
        Game game = new Game();
        game.setState(null);
        assertNotNull(game);
    }

    @Test
    void TestSetStateMenu(){
        Game game = new Game();
        MenuState menuState = new MenuState(new Menu());
        game.setState(menuState);
        assertNotNull(game);
    }
    @Test
    void TestMultipleSteState() throws IOException {
        Game game = new Game();
        game.setState((new MenuState(new Menu())));
        game.setState(null);
        game.setState((new MenuState(new Menu())));
        assertNotNull(game);
    }

    @Test
    void TestScaleCycle() throws IOException {
        com.Nunes.aqua_and_ignis.view.Scale.setScale(1);
        Game game = new Game();
        game.recreateGUI();
        game.recreateGUI();
        game.recreateGUI();
        assertEquals(1, com.Nunes.aqua_and_ignis.view.Scale.SCALE);
    }
}
