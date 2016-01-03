package io.kry.adventofenterprise.days.six.object;

import io.kry.adventofenterprise.days.six.enums.State;
import io.kry.adventofenterprise.days.six.enums.StateInstruction;
import io.kry.adventofenterprise.days.six.factories.BoundsFactory;
import io.kry.adventofenterprise.days.six.factories.LightRegionFactory;
import io.kry.adventofenterprise.exceptions.ParseInstructionException;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.days.six.factories.LightFactory;

import java.util.Arrays;

public class LightSwitchboard {

    private Light[] lights;

    public LightSwitchboard(Mode mode) {
        lights = new Light[1000000];
        for (int i = 0; i < 1000000; i++) {
            lights[i] = LightFactory.createLight(State.OFF, mode);
        }
    }

    public void processInstruction(String instructionString) throws TaskException {
        try {
            StateInstruction instruction = StateInstruction.fromString(instructionString);
            LightRegionFactory.fromBounds(BoundsFactory.fromInstruction(instructionString), lights).applyInstruction(instruction);
        } catch (ParseInstructionException e) {
            e.printStackTrace();
            throw new TaskException("Failed to parse instruction for day six: " + instructionString);
        }
    }
    public int getNumOfState(State state) {
        return Arrays.asList(lights).stream().map(Light::getState).filter(s -> s == state).toArray().length;
    }

    public int getTotalBrightness() {
        return Arrays.asList(lights).stream().mapToInt(Light::getBrightness).sum();
    }

    public enum Mode {
        BOOLEAN, BRIGHTNESS
    }
}