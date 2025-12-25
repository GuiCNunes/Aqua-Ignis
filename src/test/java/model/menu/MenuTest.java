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

    @Test
    void InitialSelectionTest() {
        Menu menu = new Menu();
        assertTrue(menu.isSelectedStart());
        assertFalse(menu.isSelectedHelp());
        assertFalse(menu.isSelectedExit());
    }

    @Test
    void HelpSelectionTest() {
        Menu menu = new Menu();
        menu.nextEntry();
        assertTrue(menu.isSelectedHelp());
    }

    @Test
    void SizeSelectionTest() {
        Menu menu = new Menu();
        menu.previousEntry();
        assertTrue(menu.isSelectedSize());
    }
}
