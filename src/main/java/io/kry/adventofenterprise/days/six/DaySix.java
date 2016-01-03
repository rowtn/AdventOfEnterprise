package io.kry.adventofenterprise.days.six;

import io.kry.adventofenterprise.days.one.object.Elevator;
import io.kry.adventofenterprise.days.six.enums.State;
import io.kry.adventofenterprise.days.six.object.LightSwitchboard;
import io.kry.adventofenterprise.exceptions.LiftException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DaySix implements Day {

    public static final int ID = 6;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("6.input")){
            LightSwitchboard switchboardBoolean = new LightSwitchboard(LightSwitchboard.Mode.BOOLEAN);
            LightSwitchboard switchboardBrightness = new LightSwitchboard(LightSwitchboard.Mode.BRIGHTNESS);
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                switchboardBoolean.processInstruction(input);
                switchboardBrightness.processInstruction(input);
            }
            return new TaskAnswer(switchboardBoolean.getNumOfState(State.ON), switchboardBrightness.getTotalBrightness());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException("Could not load file for Day One");
        }
    }
}
