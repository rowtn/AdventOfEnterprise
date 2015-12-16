package io.kry.adventofenterprise.days.two.object;

import java.util.Arrays;

public class Present {

    int slack;
    int x, y, z;
    int totalArea;

    public Present(int... data) {
        Arrays.sort(data);
        this.x = data[0];
        this.y = data[1];
        this.z = data[2];
        this.slack = x * y;
        this.totalArea = 2 * ((this.x * this.y) + (this.y * this.z) + (this.x * this.z));
    }

    public Present(String[] data) {
        this(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    public int calculateRequiredWrappingPaper() {
        return this.slack + this.totalArea;
    }

    public int calculateRequiredRibbon() {
        return 2 * (x + y) + (x * y * z);
    }
}
