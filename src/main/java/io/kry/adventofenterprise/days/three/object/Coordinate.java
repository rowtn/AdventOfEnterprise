package io.kry.adventofenterprise.days.three.object;

public class Coordinate {

    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate compare = (Coordinate) obj;
            return compare.x == this.x && compare.y == this.y;
        }
        return false;
    }

    @Override
    public Coordinate clone() throws CloneNotSupportedException {
        return new Coordinate(this.x, this.y);
    }
}
