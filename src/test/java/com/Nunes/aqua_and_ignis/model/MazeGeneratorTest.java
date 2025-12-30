package com.Nunes.aqua_and_ignis.model;


import static org.junit.jupiter.api.Assertions.*;
import com.Nunes.aqua_and_ignis.model.entity.Star;

import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    @Test
    void GeneratePlayersTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getAqua());
        assertNotNull(maze.getIgnis());
        assertNotEquals(maze.getAqua().getPosition(), maze.getIgnis().getPosition());
    }

    @Test
    void GenerateExitTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getExit());
        assert(maze.getExit().getX() == 0 || maze.getExit().getX() == 49);
    }
    @Test
    void GenerateKeyTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getKey());
        assertNotNull(maze.getKey().getPosition());
    }
    @Test
    void GenerateStarsTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertNotNull(maze.getStars());
        assertEquals(3,maze.getStars().size());
    }
    @Test
    void PlayerPositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int centerX = 25;
        int centerY = 25;

        assertEquals(centerX - 1, maze.getIgnis().getPosition().getX());
        assertEquals(centerY, maze.getIgnis().getPosition().getY());
        assertEquals(centerX + 1, maze.getAqua().getPosition().getX());
        assertEquals(centerY, maze.getAqua().getPosition().getY());
    }
    @Test
    void ExitNotWallTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        assertFalse(maze.isWall(exitPosition.getX(),  exitPosition.getY()));
    }
    @Test
    void GetFreePositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position FreePosition = generatorTest.getFreePosition(maze);
        assertFalse(maze.isWall(FreePosition.getX(), FreePosition.getY()));
        assertNotNull(FreePosition);
    }

    @Test
    void MazeHasPathsTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int FreeSpace = 0;
        for (int y = 0; y < maze.getHeight(); y++){
            for (int x = 0; x < maze.getWidth(); x++){
                if (!maze.isWall(x, y)){
                    FreeSpace ++;
                }
            }

        }
        assertTrue(FreeSpace > 0);
    }
    @Test
    void StarNotWallTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        for(Star star : maze.getStars()){
            assertFalse(maze.isWall(star.getPosition().getX(), star.getPosition().getY()));
        }
    }
    @Test
    void ExitYEvenTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int exitY = maze.getExit().getY();
        assertEquals(0, exitY %2);
    }
    @Test
    void CenterFreePositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertFalse(maze.isWall(25,25));
    }
    @Test
    void InitialWallSetTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int wallCount = 0;
        for(int y=0; y <50;y++){
            for (int x = 0; x < 50; x++){
                if (maze.isWall(x, y)) wallCount++;
            }
        }
        assertTrue(wallCount > 0);
    }

    @Test
    void EvenDimensionTest() {
        Maze maze = new Maze(48,48);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertEquals(23,maze.getIgnis().getPosition().getX());
        assertEquals(25,maze.getAqua().getPosition().getX());
        assertEquals(24,maze.getIgnis().getPosition().getY());
        assertEquals(24,maze.getAqua().getPosition().getY());
    }
    @Test
    void PathCreateAreaTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int freeSpaces = 0;
        for(int y=0; y <50;y++){
            for (int x = 0; x < 50; x++){
                if (!maze.isWall(x, y)) freeSpaces++;
            }
        }
        assertTrue(freeSpaces > 200);
    }

    @Test
    void ExitRoomCarvedTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        int exitY = maze.getExit().getY();
        int exitX = maze.getExit().getX();
        if (exitX == 0){
            boolean hasRoom = !maze.isWall(1, exitY)||!maze.isWall(2, exitY);
            assertTrue(hasRoom);
        }else{
            boolean hasRoom = !maze.isWall(47, exitY)||!maze.isWall(48, exitY);
            assertTrue(hasRoom);
        }
    }
    @Test
    void CenterRoomCarvedTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        int exitY = exitPosition.getY();
        int exitX = exitPosition.getX();
        if(exitX == 0) {
            int roomY =(exitY%2==0) ? exitY: exitY -1;
            assertFalse(maze.isWall( 1, roomY));
            assertFalse(maze.isWall(2, roomY));
            assertFalse(maze.isWall(1, roomY+1));
            assertFalse(maze.isWall(2, roomY+1));
        }else {
            int roomY =(exitY%2==0) ? exitY: exitY-1;
            assertFalse(maze.isWall( 47, roomY));
            assertFalse(maze.isWall(48, roomY));
            assertFalse(maze.isWall(47, roomY+1));
            assertFalse(maze.isWall(48, roomY+1));
        }
    }
    @Test
    void IgnisPositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int positionX= 24;
        int positionY=25;
        assertEquals(positionX, maze.getIgnis().getPosition().getX());
        assertEquals(positionY, maze.getIgnis().getPosition().getY());
    }
    @Test
    void AquaPositionTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int positionX = 26;
        int positionY = 25;
        assertEquals(positionX, maze.getAqua().getPosition().getX());
        assertEquals(positionY, maze.getAqua().getPosition().getY());
    }
    @Test
    void PathConnectStartExitTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int positionX= 25;
        int positionY=25;
        assertFalse(maze.isWall(positionX, positionY));
        assertFalse(maze.isWall(positionX, positionY+1));
        assertFalse(maze.isWall(positionX+1, positionY));
        assertFalse(maze.isWall(positionX+1, positionY+1));
    }
    @Test
    void OddStartPositionTest() {
        Maze maze = new Maze(52,54);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        assertFalse(maze.isWall(25,27));
        assertFalse(maze.isWall(26,27));
    }

    @Test
    void OddEndPositionTest() {
        Maze maze = new Maze(52,54);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int positionX= 26;
        int positionY=27;
        int freeCount = 0;
        for (int y = positionY-5; y <= positionY+5; y++){
            for (int x = positionX-5; x <= positionX+5; x++){
                if (!maze.isWall(x, y)) freeCount++;
            }
        }
        assertTrue(freeCount >= 16);
    }

    @Test
    void ExitEastOrWestTest() {
        boolean foundEast = false;
        boolean foundWest = false;
        for (int i = 0; i < 20; i++){
            Maze maze = new Maze(50,50);
            MazeGenerator generatorTest = new MazeGenerator();
            generatorTest.generate(maze);
            int exitX = maze.getExit().getX();
            if (exitX == 0) {
                foundWest = true;
            }
            if (exitX == 49) {
                foundEast = true;
            }
        }
        assertTrue(foundEast||foundWest);
    }
    @Test
    void PathCreate2x2Test() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int block = 0;
        for (int y = 1; y <= 48; y+=2){
            for  (int x = 1; x <= 48; x+=2){
                if(!maze.isWall(x, y)&&!maze.isWall(x+1,y)&&!maze.isWall(x,y+1)&&!maze.isWall(x+1,y+1)) block++;
            }
        }
        assertTrue(block >= 10);
    }
    @Test
    void ExitRoomPositionCalcTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        int exitX = maze.getExit().getX();
        int exitY = maze.getExit().getY();
        int expected = (exitY%2==0) ? exitY : exitY-1;
        if(exitX == 0) {
            assertFalse(maze.isWall(1, expected));
        }else  {
            assertFalse(maze.isWall(47, expected));
        }
    }
    @Test
    void GetFreePositionAvoidPlayerTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        for(int i = 0; i < 20; i++){
            Position free = generatorTest.getFreePosition(maze);
            assertNotEquals(maze.getIgnis().getPosition(), free);
            assertNotEquals(maze.getAqua().getPosition(), free);
        }
    }

    @Test
    void path2x2BlockTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        boolean found2x2Block = false;
        for (int y = 1; y < 48; y++){
            for (int x = 1; x < 48; x++){
                if (!maze.isWall(x,y)&&!maze.isWall(x+1,y)&&!maze.isWall(x,y+1)&&!maze.isWall(x+1,y+1)) found2x2Block = true;
                break;
            }
            if (found2x2Block) break;
        }
        assertTrue(found2x2Block);

    }
    @Test
    void ExitRoom2x2Test() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        Position exitPosition = maze.getExit();
        int exitX = maze.getExit().getX();
        int exitY = maze.getExit().getY();
        int expected = (exitY%2==0) ? exitY : exitY-1;
        if(exitX == 0) {
            int freeCount = 0;
            if (!maze.isWall(1, expected)) freeCount++;
            if (!maze.isWall(2, expected)) freeCount++;
            if (!maze.isWall(1, expected+1)) freeCount++;
            if (!maze.isWall(2, expected+1)) freeCount++;
            assertTrue(freeCount >= 2);
        }else  {
            int freeCount = 0;
            if (!maze.isWall(47, expected)) freeCount++;
            if (!maze.isWall(48, expected)) freeCount++;
            if (!maze.isWall(47, expected+1)) freeCount++;
            if (!maze.isWall(48, expected+1)) freeCount++;
            assertTrue(freeCount >= 2);
        }
    }
    @Test
    void ActualCenterRoomCarvedTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int centerX = 25;
        int centerY = 25;
        assertFalse(maze.isWall(centerX, centerY));
        assertFalse(maze.isWall(centerX-1, centerY));
        assertFalse(maze.isWall(centerX, centerY-1));
        assertFalse(maze.isWall(centerX-1, centerY-1));
        assertFalse(maze.isWall(centerX+1, centerY));
        assertFalse(maze.isWall(centerX-2, centerY));
    }
    @Test
    void InitialStartAreaClearTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int startX = 25;
        int startY = 25;
        if (startX%2 == 0) startX--;
        if (startY%2 == 0) startY--;
        assertFalse(maze.isWall(startX, startY));
        assertFalse(maze.isWall(startX+1, startY));
        assertFalse(maze.isWall(startX, startY+1));
        assertFalse(maze.isWall(startX+1, startY+1));

    }
    @Test
    void GetFreePositionNullPlayerTest() {
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        assertNull(maze.getAqua());
        assertNull(maze.getIgnis());
        Position position = generatorTest.getFreePosition(maze);
        assertNotNull(position);
        assertTrue(position.getX() >= 0&&position.getX() < 50);
    }

    @Test
    void ExitYAlwaysAlignedLoopTest(){
        for(int i = 0; i < 50; i++){
            Maze maze = new Maze(50,50);
            MazeGenerator generatorTest = new MazeGenerator();
            generatorTest.generate(maze);
            assertEquals(0, maze.getExit().getY()%2);
        }
    }
    @Test
    void InitialStartAreaAlwaysClearLoopTest(){
        for(int i = 0; i < 50; i++){
            Maze maze = new Maze(50,50);
            MazeGenerator generatorTest = new MazeGenerator();
            generatorTest.generate(maze);
            int startX = 25;
            int startY = 25;
            if (startX%2 == 0) startX--;
            if (startY%2 == 0) startY--;
            assertFalse(maze.isWall(startX, startY+1));
            assertFalse(maze.isWall(startX+1, startY));
        }
    }
    @Test
    void CenterRoomExtensionsTest(){
        Maze maze = new Maze(50,50);
        MazeGenerator generatorTest = new MazeGenerator();
        generatorTest.generate(maze);
        int centerX = 25;
        int centerY = 25;
        assertFalse(maze.isWall(centerX, centerY-2));
        assertFalse(maze.isWall(centerX, centerY+2));
        assertFalse(maze.isWall(centerX+2, centerY));
        assertFalse(maze.isWall(centerX-2, centerY));
    }
    @Test
    void DirectionShuffleTest(){
        int southPathCount = 0;
        int totalrun = 50;
        for (int i = 0; i < totalrun; i++) {
            Maze maze = new Maze(50,50);
            MazeGenerator generatorTest = new MazeGenerator();
            generatorTest.generate(maze);
            if (!maze.isWall(25,28)){
                southPathCount++;
            }
        }
        assertTrue(southPathCount < totalrun);
    }

}