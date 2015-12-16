package io.kry.adventofenterprise.days.two.factories;

import io.kry.adventofenterprise.days.two.object.Present;

public class PresentFactory {

    private static final String DELIMINATOR = "x";

    public static Present fromString(String data) {
        return new Present(data.split(DELIMINATOR));
    }
}
