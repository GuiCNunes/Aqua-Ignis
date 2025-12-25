package model.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MenuTest {
    @Test
    void CreateMenuTest() {
        Menu menu = new Menu();
        assertNotNull(menu);
    }
    
}
