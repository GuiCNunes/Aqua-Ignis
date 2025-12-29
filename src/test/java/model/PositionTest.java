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

    @Test
    void getUpDecreasesY(){
        Position position = new Position(5,8);
        Position positionUp = position.getUp();
        assertEquals(5,positionUp.getX());
        assertEquals(7,positionUp.getY());
    }

    @Test
    void getDownIncreasesY(){
        Position position = new Position(5,8);
        Position positionDown = position.getDown();
        assertEquals(5,positionDown.getX());
        assertEquals(9,positionDown.getY());
    }

    @Test
    void getLeftDecreasesX(){
        Position position = new Position(5,8);
        Position positionLeft = position.getLeft();
        assertEquals(4,positionLeft.getX());
        assertEquals(8,positionLeft.getY());
    }

    @Test
    void getRightIncreasesX(){
        Position position = new Position(5,8);
        Position positionRight = position.getRight();
        assertEquals(6,positionRight.getX());
        assertEquals(8,positionRight.getY());
    }

    @Test
    void NavigationDoesNotModifyOriginal(){
        Position original = new Position(5,8);
        original.getUp();
        original.getDown();
        original.getLeft();
        original.getRight();
        assertEquals(5,original.getX());
        assertEquals(8,original.getY());
    }
    @Test
    void GetNeighbourReturnAdjacentPosition(){
        Position position = new Position(5,8);
        Position neighbour = position.getRandomNeighbour();
        boolean IsValid = (neighbour.getX() == 6 && neighbour.getY() == 8)||(neighbour.getX() == 4 && neighbour.getY() == 8)||(neighbour.getX() == 5 && neighbour.getY() == 9)||(neighbour.getX() == 5 && neighbour.getY() == 7);
        assertTrue(IsValid);
    }

    @Test
    void GetNeighbourCoverageTest(){
        Position position = new Position(5,8);
        boolean foundUp = false;
        boolean foundDown = false;
        boolean foundLeft = false;
        boolean foundRight = false;
        for(int i = 0; i < 50; i++){
            Position n = position.getRandomNeighbour();
            if(n.equals(position.getUp())) foundUp = true;
            if(n.equals(position.getDown())) foundDown = true;
            if(n.equals(position.getLeft())) foundLeft = true;
            if(n.equals(position.getRight())) foundRight = true;
        }
        assertTrue(foundUp||foundDown||foundLeft||foundRight);
    }
    @Test
    void equalReturnTrueSamePosition(){
        Position position1 = new Position(5,8);
        Position position2 = new Position(5,8);
        assertEquals(position1,position2);
    }

    @Test
    void equalReturnTrueSameObject(){
        Position position1 = new Position(5,8);
        assertEquals(position1,position1);
    }
    @Test
    void equalReturnFalseDiferentX(){
        Position position1 = new Position(5,8);
        Position position2 = new Position(4,8);
        assertNotEquals(position1,position2);
    }
    @Test
    void equalReturnFalseDiferentY(){
        Position position1 = new Position(5,8);
        Position position2 = new Position(5,9);
        assertNotEquals(position1,position2);
    }
    @Test
    void equalReturnFalseNull(){
        Position position1 = new Position(5,8);
        assertNotEquals(position1,null);
    }
    @Test
    void equalReturnFalseDifferentClass(){
        Position position1 = new Position(5,8);
        String differentClass = "differentClass";
        assertNotEquals(position1,differentClass);
    }

    @Test
    void HashCodeSameEqualPosition(){
        Position position1 = new Position(5,8);
        Position position2 = new Position(5,8);
        assertEquals(position1.hashCode(),position2.hashCode());
    }
    @Test
    void HashCodeDifferentDifferentPosition(){
        Position position1 = new Position(5,8);
        Position position2 = new Position(5,9);
        assertNotEquals(position1.hashCode(),position2.hashCode());
    }
    @Test
    void NegativeCoordinatesTest(){
        Position position1 = new Position(-5,-8);
        assertEquals(-5,position1.getX());
        assertEquals(-8,position1.getY());
    }
    @Test
    void NullCoordinatesTest(){
        Position position1 = new Position(0,0);
        assertEquals(0,position1.getX());
        assertEquals(0,position1.getY());
    }
}
