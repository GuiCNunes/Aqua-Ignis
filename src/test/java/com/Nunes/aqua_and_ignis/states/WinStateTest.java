package com.Nunes.aqua_and_ignis.states;
import com.Nunes.aqua_and_ignis.model.menu.Menu;
import com.Nunes.aqua_and_ignis.states.WinState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinStateTest {
    @Test
    void CreateStateTest() {
        Menu menu = new Menu();
        WinState winState = new WinState(menu, 1, 1L);
        assertNotNull(winState);
    }
    @Test
    void GetModelTest() {
        Menu menu = new Menu();
        WinState winState = new WinState(menu, 1, 1L);
        assertEquals(menu, winState.getModel());
    }
    @Test
    void GetViewTest() {
        Menu menu = new Menu();
        WinState winState = new WinState(menu, 1, 1L);
        assertNotNull(winState.getView());
    }
    @Test
    void GetControllerTest() {
        Menu menu = new Menu();
        WinState winState1 = new WinState(menu, 1, 1L);
        WinState winState2 = new WinState(menu, 2, 2L);
        WinState winState3 = new WinState(menu, 3, 3L);
        assertNotNull(winState1.getController());
        assertNotNull(winState2.getController());
        assertNotNull(winState3.getController());
    }
}
