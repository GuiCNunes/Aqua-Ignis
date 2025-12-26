package model.entity;

import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void TestCreationAqua() {
        Aqua aqua = new Aqua(15, 20);
        assertNotNull(aqua);
        assertEquals(15, aqua.getPosition().getX());
        assertEquals(20, aqua.getPosition().getY());
    }

    @Test
    void TestCreationIgnis() {
        Ignis ignis = new Ignis(17, 28);
        assertNotNull(ignis);
        assertEquals(17, ignis.getPosition().getX());
        assertEquals(28, ignis.getPosition().getY());
    }

    @Test
    void TestPlayerMov() {
        Aqua aqua = new Aqua(15, 15);

        aqua.setPosition(new Position(15, 14));
        assertEquals(15, aqua.getPosition().getX());
        assertEquals(14, aqua.getPosition().getY());

        aqua.setPosition(new Position(15, 16));
        assertEquals(15, aqua.getPosition().getX());
        assertEquals(16, aqua.getPosition().getY());
    }

    @Test
    void TestSetPlayerPosition() {
        Ignis ignis = new Ignis(5, 5);
        Position newPosition = new Position(50, 30);
        ignis.setPosition(newPosition);

        assertEquals(50, ignis.getPosition().getX());
        assertEquals(30, ignis.getPosition().getY());
    }
}
