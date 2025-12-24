package model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public Position getUp() { return new Position(x, y - 1); }
    public Position getDown() { return new Position(x, y + 1); }
    public Position getLeft() { return new Position(x - 1, y); }
    public Position getRight() { return new Position(x + 1, y); }

    public Position getRandomNeighbour() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0: return getDown();
            case 1: return getUp();
            case 2: return getRight();
            default: return getLeft();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}