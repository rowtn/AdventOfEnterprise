package io.kry.adventofenterprise.days.six.factories;

import io.kry.adventofenterprise.days.six.object.Bounds;
import io.kry.adventofenterprise.days.six.object.Light;
import io.kry.adventofenterprise.days.six.object.LightRegion;

public class LightRegionFactory {

    private static final boolean DEBUG = false;

    public static LightRegion fromBounds(Bounds bounds, Light[] original) {

        Light[] subset = new Light[bounds.getArea()];
        int i = 0;
        for (int x = bounds.getX1(); x < bounds.getX2() + 1; x++) {
            for (int y = bounds.getY1(); y < bounds.getY2() + 1; y++) {
                subset[i] = original[y * 999 + x];
                i++;
            }
        }


        if (DEBUG) {
            System.out.println("____BOUNDS DEBUG____");
            System.out.println(bounds.getX1());
            System.out.println(bounds.getX2());
            System.out.println(bounds.getY1());
            System.out.println(bounds.getY2());
            System.out.println(bounds.getArea());
            System.out.println("____BOUNDS DEBUG____");
            System.out.println("looped " + i + " times");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new LightRegion(subset);
    }
}
