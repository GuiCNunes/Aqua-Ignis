package com.Nunes.aqua_and_ignis.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTest {

    @Test
    void glyphsStartInThePrivateUseArea() {
        assertEquals(0xE000, Tile.FLOOR.glyph());
        assertEquals(0xE001, Tile.WALL.glyph());
    }
}
