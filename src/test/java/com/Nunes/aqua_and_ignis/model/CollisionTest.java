package com.Nunes.aqua_and_ignis.model;
/*
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.entity.Slime;
import model.entity.Ghost;

class CollisionTest {

    @Test
    void TestPlayersPositionsDif() {
        Maze maze = new Maze(50, 50);
        Position aquaPosition = maze.getAqua().getPosition();
        Position ignisPosition = maze.getIgnis().getPosition();

        assertFalse((aquaPosition.getX() == ignisPosition.getX()) && (aquaPosition.getY() == ignisPosition.getY()));
    }

    @Test
    void TestMonsterPositionsDif() {
        Maze maze = new Maze(50, 50);
        maze.addMonster(new Slime(10, 10));
        maze.addMonster(new Ghost(30, 30));
        assertEquals(2, maze.getMonsters().size());

        Position Monster1Position = maze.getMonsters().get(0).getPosition();
        Position Monster2Position = maze.getMonsters().get(1).getPosition();

        assertFalse((Monster1Position.getX() == Monster2Position.getX()) && (Monster1Position.getY() == Monster2Position.getY()));
    }

    @Test
    void testExitOnPlayer() {
        Maze maze = new Maze(50, 50);
        Position exit = maze.getExit();
        Position aqua = maze.getAqua().getPosition();
        Position ignis = maze.getIgnis().getPosition();

        assertFalse(exit.getX() == aqua.getX() && exit.getY() == aqua.getY());
        assertFalse(exit.getX() == ignis.getX() && exit.getY() == ignis.getY());
    }
}
*/