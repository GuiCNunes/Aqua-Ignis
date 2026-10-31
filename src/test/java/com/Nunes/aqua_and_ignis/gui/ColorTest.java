package com.Nunes.aqua_and_ignis.gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void accessors() {
        Color color = new Color(10, 20, 30);
        assertEquals(10, color.r());
        assertEquals(20, color.g());
        assertEquals(30, color.b());
    }

    @Test
    void ofHexWithHash() {
        Color color = Color.ofHex("#FFFFFF");
        assertEquals(255, color.r());
        assertEquals(255, color.g());
        assertEquals(255, color.b());
    }

    @Test
    void ofHexWithoutHash() {
        Color color = Color.ofHex("000000");
        assertEquals(0, color.r());
        assertEquals(0, color.g());
        assertEquals(0, color.b());
    }

    @Test
    void ofHexValues() {
        Color color = Color.ofHex("#123456");
        assertEquals(18, color.r());
        assertEquals(52, color.g());
        assertEquals(86, color.b());
    }

    @Test
    void scaledDarkensEveryChannel() {
        assertEquals(new Color(50, 100, 125), new Color(100, 200, 250).scaled(0.5));
    }

    @Test
    void scaledByOneKeepsTheColor() {
        Color color = new Color(12, 34, 56);
        assertEquals(color, color.scaled(1.0));
    }

    @Test
    void scaledRoundsToTheNearestValue() {
        assertEquals(new Color(1, 2, 3), new Color(3, 5, 7).scaled(0.4));
    }

    @Test
    void scaledStaysInsideZeroAndTwoFiftyFive() {
        assertEquals(new Color(255, 255, 255), new Color(200, 255, 100).scaled(10));
        assertEquals(new Color(0, 0, 0), new Color(200, 255, 100).scaled(-1));
    }

    @Test
    void equality() {
        Color c1 = new Color(100, 100, 100);
        Color c2 = new Color(100, 100, 100);
        Color c3 = new Color(0, 0, 0);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertEquals(c1.toString(), c2.toString());
    }
}