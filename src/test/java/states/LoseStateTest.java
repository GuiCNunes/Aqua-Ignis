package states;

import model.menu.Menu;
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

