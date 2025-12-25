package model.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MenuTest {
    @Test
    void CreateMenuTest() {
        Menu menu = new Menu();
        assertNotNull(menu);
    }

    @Test
    void EntriesNumberTest() {
        Menu menu = new Menu();
        assertEquals(4, menu.getNumberEntries());
    }
}
