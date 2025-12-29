package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.states.LoseState;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
class LoseStateTest {
    @Test
    void CreateStateTest(){
        Menu menu = new Menu();
        LoseState loseState = new LoseState(menu);
        assertNotNull(loseState);
    }

    @Test
    void GetModelTest(){
        Menu menu = new Menu();
        LoseState loseState = new LoseState(menu);
        assertEquals(menu, loseState.getModel());
    }

    @Test
    void GetViewTest(){
        Menu menu = new Menu();
        LoseState loseState = new LoseState(menu);
        assertNotNull(loseState.getView());
    }



    @Test
    void getControllerTest(){
        Menu menu = new Menu();
        LoseState loseState = new LoseState(menu);
        assertNotNull(loseState.getController());
    }
}

