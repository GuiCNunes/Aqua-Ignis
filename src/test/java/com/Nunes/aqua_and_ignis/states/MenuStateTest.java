package com.Nunes.aqua_and_ignis.states;

import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.states.MenuState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuStateTest {
    @Test
    void CreateStateTest() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);
        assertNotNull(menuState);
    }

    @Test
    void GetModelTest() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);
        assertEquals(menu, menuState.getModel());
    }

    @Test
    void GetViewTest() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);
        assertNotNull(menuState.getView());
    }

    @Test
    void GetControllerTest() {
        Menu menu = new Menu();
        MenuState menuState = new MenuState(menu);
        assertNotNull(menuState.getController());
    }
}
