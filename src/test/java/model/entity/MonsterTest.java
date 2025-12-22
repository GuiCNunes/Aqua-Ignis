package model.entity;

import model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    @Test
    void TestCreateGhost() {
        Ghost ghost = new Ghost(10, 12);
        assertNotNull(ghost);
        assertEquals(10, ghost.getPosition().getX());
        assertEquals(12, ghost.getPosition().getY());
    }

    @Test
    void TestCreateSlime() {
        Slime slime = new Slime(20, 16);
        assertNotNull(slime);
        assertEquals(20, slime.getPosition().getX());
        assertEquals(16, slime.getPosition().getY());
    }

    @Test
    void TestMonsterMov() {
        Ghost ghost = new Ghost(10, 12);

        Position left = ghost.moveLeft();
        assertEquals(9, left.getX());
        assertEquals(12, left.getY());

        Position right = ghost.moveRight();
        assertEquals(11, right.getX());
        assertEquals(12, right.getY());
    }

    @Test
    void TestSetMonsterPosition() {
        Slime slime = new Slime(16, 21);
        Position NewPosition = new Position(10, 21);
        slime.setPosition(NewPosition);

        assertEquals(10, slime.getPosition().getX());
        assertEquals(21, slime.getPosition().getY());
    }
}
