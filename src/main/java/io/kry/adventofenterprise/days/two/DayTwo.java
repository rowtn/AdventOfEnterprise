package io.kry.adventofenterprise.days.two;

import io.kry.adventofenterprise.days.two.factories.PresentFactory;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.days.two.object.Present;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DayTwo implements Day {

    public static final int ID = 2;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("2.input")) {
            int totalWrappingPaper = 0;
            int totalRibbon = 0;
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                Present present = PresentFactory.fromString(scanner.nextLine());
                totalWrappingPaper += present.calculateRequiredWrappingPaper();
                totalRibbon += present.calculateRequiredRibbon();
            }
            System.out.println(totalWrappingPaper);
            return new TaskAnswer(totalWrappingPaper, totalRibbon);
        } catch (IOException e) {
            throw new TaskException("File for DayTwo could not be loaded");
        }
    }

}
