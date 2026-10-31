package com.Nunes.aqua_and_ignis.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LayoutTest {

    @Test
    void worldIsMovedBelowTheHud() {
        assertEquals(1, Layout.worldX(0));
        assertEquals(2, Layout.worldY(0));
        assertEquals(90, Layout.worldX(89));
        assertEquals(31, Layout.worldY(29));
    }
}
