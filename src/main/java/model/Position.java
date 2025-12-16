package model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Position getRandomNeighbour() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                return new Position(x, y + 1);
            case 1:
                return new  Position(x, y - 1);
            case 2:
                return new  Position(x + 1, y);
            default:
                return new Position(x-1,y);
        }
    }

}
