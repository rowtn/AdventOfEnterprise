package io.kry.adventofenterprise.days.fourteen;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFourteen implements Day {

    public static final int ID = 0;

    /* Dancer can fly 7 km/s for 20 seconds, but then must rest for 119 seconds. */

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("14.input"))) {
            Map<String, Reindeer>  reindeers = new HashMap<>();
            Pattern p = Pattern.compile("([A-z]+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds\\.");
            while (scanner.hasNextLine()) {
                Matcher m = p.matcher(scanner.nextLine());
                reindeers.put(m.group(1), Reindeer.builder().speed(m.group(2)).movetime(m.group(3)).waitTime(m.group(4)).build());
            }
            return new TaskAnswer(0, 0);
        }
    }

    static class Reindeer {
        private int speed, moveTime, waitTime, travelled, currentTick = 0;

        public Reindeer(int speed, int moveTime, int waitTime) {
            this.speed = speed;
            this.moveTime = moveTime;
            this.waitTime = waitTime;
        }

        static ReindeerBuilder builder() {
            return new ReindeerBuilder();
        }

        public void tick() {
            currentTick++;
        }
    }

    static class ReindeerBuilder {
        private int speed, moveTime, waitTime;

        public ReindeerBuilder movetime(String moveTime) {
            this.moveTime = Integer.parseInt(moveTime);
            return this;
        }

        public ReindeerBuilder waitTime(String waitTime) {
            this.waitTime = Integer.parseInt(waitTime);
            return this;
        }

        public ReindeerBuilder speed(String speed) {
            this.speed = Integer.parseInt(speed);
            return this;
        }

        public Reindeer build() {
            return new Reindeer(speed, moveTime, waitTime);
        }
    }
}

