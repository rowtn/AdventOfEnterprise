package io.kry.adventofenterprise.days.eight;

import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DayEight implements Day {

    public static final int ID = 8;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("8.input"))) {
            int total = 0, escaped = 0;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                total += str.length();
                str = str.replaceAll("\\\\\"", "B").replaceAll("\"", "").replaceAll("\\\\x[0-9a-f]{2}", "A");
                escaped += str.length();

                System.out.println("raw: " + total);
                System.out.println("esc: " + escaped);
                System.out.println("diff: " + (total - escaped));
            }
            return new TaskAnswer(total - escaped, 0);
        }
    }
}
