package states;

import static org.junit.jupiter.api.Assertions.*;

import model.menu.Menu;
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
