package io.kry.adventofenterprise.days.six.factories;

import io.kry.adventofenterprise.days.six.object.Bounds;
import io.kry.adventofenterprise.days.six.object.Light;
import io.kry.adventofenterprise.days.six.object.LightRegion;

public class LightRegionFactory {

    public static LightRegion fromBounds(Bounds bounds, Light[] original) {
        Light[] subset = new Light[bounds.getArea()];
        int i = 0;
        for (int x = bounds.getX1(); x <= bounds.getX2(); x++) {
            for (int y = bounds.getY1(); y <= bounds.getY2(); y++) {
                subset[i] = original[y * 999 + x];
                i++;
            }
        }
        return new LightRegion(subset);
    }
}
