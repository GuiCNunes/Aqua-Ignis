package com.Nunes.aqua_and_ignis.model;

import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void TestMazeCreation() {
        Maze maze = new Maze(20, 50);
        assertEquals(20, maze.getWidth());
        assertEquals(50, maze.getHeight());
    }

    @Test
    void TestMonsterCreation() {
        Maze maze = new Maze(40, 50);
        assertNotNull(maze.getMonsters());
        maze.addMonster(new Ghost(20, 20));
        maze.addMonster(new Slime(30, 30));
        assertEquals(2, maze.getMonsters().size());
    }
    @Test
    void TestMoveOutBounds() {
        Maze maze = new Maze(60, 50);
        Position OutBounds = new  Position(-1,10);
        assertTrue(OutBounds.getX() < 0);
    }
    @Test
    void TestSetAndGetIgnis() {
        Maze maze = new Maze(20, 50);
        Ignis ignis = new Ignis(5,5);
        maze.setIgnis(ignis);
        assertEquals(ignis,maze.getIgnis());
    }

    @Test
    void TestSetAndGetAqua() {
        Maze maze = new Maze(20, 50);
        Aqua aqua = new Aqua(5,5);
        maze.setAqua(aqua);
        assertEquals(aqua,maze.getAqua());
    }
    @Test
    void TestIgnisStartNull() {
        Maze maze = new Maze(20, 50);
        assertNull(maze.getIgnis());
    }
    @Test
    void TestAquaStartNull() {
        Maze maze = new Maze(20, 50);
        assertNull(maze.getAqua());
    }

    @Test
    void TestSetAndGetExit() {
        Maze maze = new Maze(20, 50);
        Position Exit = new  Position(5,5);
        maze.setExit(Exit);
        assertEquals(Exit,maze.getExit());
    }

    @Test
    void TestExitStartNull() {
        Maze maze = new Maze(20, 50);
        assertNull(maze.getExit());
    }

    @Test
    void TestExitStartClosed(){
        Maze maze = new Maze(20, 50);
        assertFalse(maze.isExitOpen());
    }
    @Test
    void TestExitCanOpen(){
        Maze maze = new Maze(20, 50);
        maze.setExitOpen(true);
        assertTrue(maze.isExitOpen());
    }
    @Test
    void TestExitCanClose(){
        Maze maze = new Maze(20, 50);
        maze.setExitOpen(true);
        maze.setExitOpen(false);
        assertFalse(maze.isExitOpen());
    }
    @Test
    void TestSetAndGetKey() {
        Maze maze = new Maze(20, 50);
        Key key = new Key(5,5);
        maze.setKey(key);
        assertEquals(key,maze.getKey());
    }
    @Test
    void TestKeyStartNull() {
        Maze maze = new Maze(20, 50);
        assertNull(maze.getKey());
    }

    @Test
    void TestStarListStartEmpty() {
        Maze maze = new Maze(20, 50);
        assertNotNull(maze.getStars());
        assertEquals(0,maze.getStars().size());
    }

    @Test
    void TestCollectStarStartEmpty() {
        Maze maze = new Maze(20, 50);
        assertEquals(0, maze.getCollectedStars());
    }
    @Test
    void TestIncrementStar(){
        Maze maze = new Maze(20, 50);
        maze.incrementStars();
        assertEquals(1, maze.getCollectedStars());
    }
    @Test
    void TestIncrementMoreStars(){
        Maze maze = new Maze(20, 50);
        maze.incrementStars();
        maze.incrementStars();
        maze.incrementStars();
        assertEquals(3, maze.getCollectedStars());
    }
    @Test
    void TestWallStartFalse() {
        Maze maze = new Maze(20, 50);
        assertFalse(maze.isWall(10,10));
    }
    @Test
    void TestSetWallTrue() {
        Maze maze = new Maze(20, 50);
        maze.setWall(10,10,true);
        assertTrue(maze.isWall(10,10));
    }
    @Test
    void TestSetWallFalse() {
        Maze maze = new Maze(20, 50);
        maze.setWall(10,10,false);
        assertFalse(maze.isWall(10,10));
    }

    @Test
    void TestDifferentPositionDifferentWall(){
        Maze maze = new Maze(20, 50);
        maze.setWall(10,10,true);
        assertTrue (maze.isWall(10,10));
        assertFalse (maze.isWall(10,9));
        assertFalse (maze.isWall(9,10));
    }

    @Test
    void TestMemoryStartFalse() {
        Maze maze = new Maze(20, 50);
        assertFalse(maze.isMemorized(5,5));
    }

    @Test
    void TestUpdateMemoryPosition(){
        Maze maze = new Maze(20, 50);
        maze.updateMemory(5,5);
        assertTrue(maze.isMemorized(5,5));
    }

    @Test
    void TestUpdateMemoryDontChangeOtherPosition(){
        Maze maze = new Maze(20, 50);
        maze.updateMemory(5,5);
        assertTrue(maze.isMemorized(5,5));
        assertFalse (maze.isMemorized(5,6));
    }
    @Test
    void TestMemoryFalseNegativeX(){
        Maze maze = new Maze(20, 50);
        assertFalse(maze.isMemorized(-1,5));
    }

    @Test
    void TestMemoryFalseNegativeY(){
        Maze maze = new Maze(20, 50);
        assertFalse(maze.isMemorized(5,-1));
    }

    @Test
    void TestMemoryFalseOutOfBoundsX() {
        Maze maze = new Maze(20, 50);
        assertFalse((maze.isMemorized(20,5)));
    }

    @Test
    void TestMemoryFalseOutOfBoundsY() {
        Maze maze = new Maze(20, 50);
        assertFalse((maze.isMemorized(5,50)));
    }
    @Test
    void TestUpdateMempryIgnoreWall(){
        Maze maze = new Maze(20, 50);
        maze.setWall(10,10,true);
        maze.updateMemory(10,10);
        assertFalse(maze.isMemorized(10,10));
    }

    @Test
    void TestUpdateMemoryIgnoreOutOfBound(){
        Maze maze = new Maze(20, 50);
        maze.updateMemory(-1,10);
        maze.updateMemory(10,-1);
        maze.updateMemory(20,10);
        maze.updateMemory(10,50);
        assertTrue(true);
    }
    @Test
    void TestMonsterListStartEmpty() {
        Maze maze = new Maze(20, 50);
        assertEquals(0,maze.getMonsters().size());
    }
    @Test
    void TestAddMonsters(){
        Maze maze = new Maze(20, 50);
        maze.addMonster(new Slime(5,5));
        maze.addMonster(new Slime(10, 5));
        maze.addMonster(new Ghost(5,10));
        assertEquals(3,maze.getMonsters().size());
    }
    @Test
    void TestGetTime(){
        Maze maze = new Maze(20, 50);
        long time = maze.getTime();
        assertTrue(time >= 0);
    }

    @Test
    void TestTimeIncrement() throws InterruptedException {
        Maze maze = new Maze(20, 50);
        long time1 = maze.getTime();
        Thread.sleep(10000);
        long time2 = maze.getTime();
        assertTrue(time2 >= time1);
    }
}
