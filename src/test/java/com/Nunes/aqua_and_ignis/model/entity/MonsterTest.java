package com.Nunes.aqua_and_ignis.model.entity;

import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.Ghost;
import com.Nunes.aqua_and_ignis.model.entity.Slime;
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

        ghost.setPosition(new Position(9, 12));
        assertEquals(9, ghost.getPosition().getX());
        assertEquals(12, ghost.getPosition().getY());

        ghost.setPosition(new Position(11, 12));
        assertEquals(11, ghost.getPosition().getX());
        assertEquals(12, ghost.getPosition().getY());
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
