package com.Nunes.aqua_and_ignis.states;

import static org.junit.jupiter.api.Assertions.*;

import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.states.HelpState;
import org.junit.jupiter.api.Test;
class HelpStateTest {
    @Test
    void CreateStateTest() {
        Menu menu = new Menu();
        HelpState helpState = new HelpState(menu);

        assertNotNull(helpState);
    }

    @Test
    void GetModelTest() {
        Menu menu = new Menu();
        HelpState helpState = new HelpState(menu);
        assertEquals(menu, helpState.getModel());
    }
    @Test
    void GetViewTest() {
        Menu menu = new Menu();
        HelpState helpState = new HelpState(menu);
        assertNotNull(helpState.getView());
    }
    @Test
    void GetControllerTest() {
        Menu menu = new Menu();
        HelpState helpState = new HelpState(menu);
        assertNotNull(helpState.getController());
    }
}
