package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightingTest {
    private final Lighting lighting = new Lighting(20);
    private final Position aqua = new Position(10, 10);
    private final Position ignis = new Position(50, 10);

    @Test
    void fullBrightnessAtAPlayer() {
        assertEquals(1.0, lighting.brightness(10, 10, aqua, ignis), 0.0001);
        assertEquals(1.0, lighting.brightness(50, 10, aqua, ignis), 0.0001);
    }

    @Test
    void dimmerFurtherFromTheNearestPlayer() {
        double near = lighting.brightness(12, 10, aqua, ignis);
        double far = lighting.brightness(20, 10, aqua, ignis);

        assertTrue(near > far);
        assertTrue(far > 0.5);
    }

    @Test
    void dimAtTheEdgeOfTheRadius() {
        assertEquals(0.5, lighting.brightness(30, 10, aqua, ignis), 0.0001);
    }

    @Test
    void neverDarkerThanTheEdge() {
        assertEquals(0.5, lighting.brightness(30, 30, aqua, ignis), 0.0001);
    }

    @Test
    void usesTheNearestPlayer() {
        assertEquals(lighting.brightness(15, 10, aqua, ignis),
                lighting.brightness(45, 10, aqua, ignis), 0.0001);
    }

    @Test
    void tileAtAPlayerIsTintedWithThePlayerColour() {
        Color white = Colors.WHITE;

        assertEquals(white.mixed(Colors.AQUA, 0.2), lighting.light(white, 10, 10, aqua, ignis));
        assertEquals(white.mixed(Colors.IGNIS, 0.2), lighting.light(white, 50, 10, aqua, ignis));
    }

    @Test
    void tintFadesAwayAtTheEdge() {
        Color color = new Color(100, 100, 100);

        assertEquals(color.scaled(0.5), lighting.light(color, 30, 10, aqua, ignis));
    }

    @Test
    void equalDistanceUsesAquaTint() {
        Color color = new Color(100, 100, 100);

        Color lit = lighting.light(color, 20, 10, aqua, new Position(30, 10));

        assertTrue(lit.b() > lit.r());
    }
}
