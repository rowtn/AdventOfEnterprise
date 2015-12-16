package io.kry.adventofenterprise.days.one;

import io.kry.adventofenterprise.days.three.object.Coordinate;
import io.kry.adventofenterprise.exceptions.LiftException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.days.one.object.Elevator;

import java.io.*;
import java.util.Scanner;

public class DayOne implements Day {

    public static final int ID = 1;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("1.input")){
            String line = new Scanner(is).nextLine();
            Elevator elevator = new Elevator();
            int currentPosition = 0;
            int firstNegativePosition = 0;
            for (char c : line.toCharArray()) {
                try {
                    currentPosition++;
                    if (elevator.processInstruction(c) == -1 && firstNegativePosition == 0)
                        firstNegativePosition = currentPosition;

                } catch (LiftException e) {
                    e.printStackTrace();
                }
            }
            return new TaskAnswer(elevator.getCurrentFloor(), firstNegativePosition);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException("Could not load file for Day One");
        }
    }
}
