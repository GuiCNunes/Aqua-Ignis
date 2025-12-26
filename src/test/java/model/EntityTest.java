package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestEntity extends Entity {
    public TestEntity(int x, int y) {
        super(x, y);
    }
}

class EntityTest {

    @Test
    void TestMoveUp() {
        Entity entity = new TestEntity(5, 5);
        Position position = entity.moveUp();
        assertEquals(5,position.getX());
        assertEquals(4,position.getY());
    }

    @Test
    void TestMoveDown() {
        Entity entity = new TestEntity(5, 5);
        Position position = entity.moveDown();
        assertEquals(5,position.getX());
        assertEquals(6,position.getY());
    }

    @Test
    void TestMoveLeft() {
        Entity entity = new TestEntity(5, 5);
        Position position = entity.moveLeft();
        assertEquals(4,position.getX());
        assertEquals(5,position.getY());
    }

    @Test
    void TestMoveRight() {
        Entity entity = new TestEntity(5, 5);
        Position position = entity.moveRight();
        assertEquals(6,position.getX());
        assertEquals(5,position.getY());
    }
}

