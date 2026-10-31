package com.Nunes.aqua_and_ignis.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTest {

    @Test
    void glyphsStartInThePrivateUseArea() {
        assertEquals(0xE000, Tile.FLOOR.glyph());
        assertEquals(0xE001, Tile.WALL_0.glyph());
        assertEquals(0xE010, Tile.WALL_15.glyph());
    }

    @Test
    void wallIsPickedFromTheNeighbourNumber() {
        assertEquals(Tile.WALL_0, Tile.wall(0));
        assertEquals(Tile.WALL_9, Tile.wall(9));
        assertEquals(Tile.WALL_15, Tile.wall(15));
    }
}
