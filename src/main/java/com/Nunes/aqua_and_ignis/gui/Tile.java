package com.Nunes.aqua_and_ignis.gui;

public enum Tile {
    FLOOR,
    WALL;

    public char glyph() {
        return (char) (0xE000 + ordinal());
    }
}
