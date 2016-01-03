package io.kry.adventofenterprise.days.fourteen;

import io.kry.adventofenterprise.days.fourteen.object.Reindeer;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFourteen implements Day {

    public static final int ID = 14;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("14.input"))) {
            Map<String, Reindeer>  reindeers = new HashMap<>();
            Pattern p = Pattern.compile("([A-z]+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\.");
            while (scanner.hasNextLine()) {
                Matcher m = p.matcher(scanner.nextLine());
                m.matches();
                reindeers.put(m.group(1), Reindeer.builder().speed(m.group(2)).movetime(m.group(3)).waitTime(m.group(4)).build());
            }
            Map<String, Integer> points = new HashMap<>();
            reindeers.keySet().forEach(s -> points.put(s, 0));
            for (int i = 0; i < 2503; i++) {
                reindeers.values().forEach(Reindeer::tick);
                double max = reindeers.values().stream().mapToInt(Reindeer::getTravelled).max().getAsInt();
                reindeers.forEach((k, v) -> {
                    if (v.getTravelled() == max) points.put(k, points.get(k) + 1);
                });
            }
            return new TaskAnswer(reindeers.values().stream().mapToInt(Reindeer::getTravelled).max().getAsInt(), points.values().stream().mapToInt(Integer::intValue).max().getAsInt());
        }
    }
}

