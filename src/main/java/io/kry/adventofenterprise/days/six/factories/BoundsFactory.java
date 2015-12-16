package io.kry.adventofenterprise.days.six.factories;

import io.kry.adventofenterprise.days.six.object.Bounds;
import io.kry.adventofenterprise.exceptions.ParseInstructionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundsFactory {

    private static final Pattern REGEX = Pattern.compile("[A-z\\s]*(\\d+),(\\d+)[A-z\\s]*(\\d+),(\\d+)");

    public static Bounds fromInstruction(String instruction) throws ParseInstructionException {
        Matcher matcher = REGEX.matcher(instruction);
        matcher.matches();
        return new Bounds(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
    }
}
