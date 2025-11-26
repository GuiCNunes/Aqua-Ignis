package model;

public abstract class Entity {
    protected Position position;

    public Entity(int x, int y) {
        this.position = new Position(x, y);

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
