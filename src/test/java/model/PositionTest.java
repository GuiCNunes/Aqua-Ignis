package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testInitialization() {
        Position position = new Position(5,8);
        assertEquals(5,position.getX());
        assertEquals(8,position.getY());
    }

    @Test
    void testSet(){
        Position position = new Position(2,4);
        position.setX(8);
        position.setY(12);
        assertEquals(8,position.getX());
        assertEquals(12,position.getY());
    }
}
