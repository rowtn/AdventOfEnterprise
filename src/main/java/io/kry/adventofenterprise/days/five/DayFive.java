package io.kry.adventofenterprise.days.five;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.days.five.object.Word;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DayFive implements Day {

    public static final int ID = 5;

    @Override
    public TaskAnswer solve() throws TaskException {

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("5.input")) {
            Scanner scanner = new Scanner(is);
            int previouslyNauhty = 0;
            int currentlyNaughty = 0;
            while (scanner.hasNextLine()) {
                Word word = new Word(scanner.nextLine());
                if (word.previouslyNaughty()) previouslyNauhty++;
                if (word.currentlyNaughty()) currentlyNaughty++;
            }
            return new TaskAnswer(previouslyNauhty, currentlyNaughty);
        } catch (IOException e) {
            throw new TaskException("Could not load file for day five");
        }
    }
}
