package com.Nunes.aqua_and_ignis.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileFontConfigurationTest {

    @Test
    void cellsAreSquare() {
        for (int size : new int[]{12, 16, 24}) {
            TileFontConfiguration configuration = new TileFontConfiguration(size);

            assertEquals(size, configuration.getFontWidth());
            assertEquals(size, configuration.getFontHeight());
        }
    }

    @Test
    void antiAliasingIsOff() {
        assertFalse(new TileFontConfiguration(16).isAntiAliased());
    }
}
