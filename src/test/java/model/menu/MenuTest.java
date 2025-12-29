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

    @Test
    void LoopTest() {
        Menu menu = new Menu();
        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelectedStart());
    }

    @Test
    void ExitSelectionTest() {
        Menu menu = new Menu();
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelectedExit());
        assertFalse(menu.isSelectedStart());
    }

    @Test
    void IsSelectedWithIndexTest() {
        Menu menu = new Menu();
        assertTrue(menu.isSelected(0));
        menu.nextEntry();
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(0));
    }

    @Test
    void GetEntryReturnsCorrectString() {
        Menu menu = new Menu();
        assertEquals("Start", menu.getEntry(0));
        assertEquals("Help", menu.getEntry(1));
        assertEquals("Exit", menu.getEntry(2));
    }

    @Test
    void GetSizeEntryIncludesScale() {
        Menu menu = new Menu();
        String sizeEntry = menu.getEntry(3);
        assertTrue(sizeEntry.startsWith("Size: "));
    }

    @Test
    void mixedNavigationTest() {
        Menu menu = new Menu();
        menu.nextEntry();
        menu.nextEntry();
        menu.previousEntry();
        assertTrue(menu.isSelectedHelp());
    }

    @Test
    void BoundaryWrapTest() {
        Menu menu = new Menu();
        menu.previousEntry();
        assertTrue(menu.isSelectedSize());
        menu.nextEntry();
        assertTrue(menu.isSelectedStart());
    }
}
