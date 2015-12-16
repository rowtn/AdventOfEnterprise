package io.kry.adventofenterprise.days.three.object;

public class GPS {

    private Coordinate coordinate;

    public GPS(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public GPS(int x, int y) {
        this(new Coordinate(x, y));
    }

    public GPS() {
        this(0, 0);
    }

    public void processInstruction(char c) {
        switch (c) {
            case '<':
                this.coordinate.x--;
                break;
            case '>':
                this.coordinate.x++;
                break;
            case '^':
                this.coordinate.y++;
                break;
            case 'v':
                this.coordinate.y--;
                break;
        }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
