package com.Nunes.aqua_and_ignis.gui;

public enum Tile {
    FLOOR,
    WALL_0, WALL_1, WALL_2, WALL_3, WALL_4, WALL_5, WALL_6, WALL_7,
    WALL_8, WALL_9, WALL_10, WALL_11, WALL_12, WALL_13, WALL_14, WALL_15,
    AQUA, IGNIS, SLIME, GHOST, STAR, KEY,
    DOOR_CLOSED, DOOR_OPEN;

    public static Tile wall(int neighbours) {
        return values()[WALL_0.ordinal() + neighbours];
    }

    public char glyph() {
        return (char) (0xE000 + ordinal());
    }
}
