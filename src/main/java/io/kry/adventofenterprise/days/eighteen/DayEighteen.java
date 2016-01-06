package io.kry.adventofenterprise.days.eighteen;

import io.kry.adventofenterprise.days.six.enums.State;
import io.kry.adventofenterprise.days.six.factories.LightFactory;
import io.kry.adventofenterprise.days.six.object.Light;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayEighteen implements Day {

    public static final int ID = 18;

    private final static char ON = '#';

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("18.input"))) {
            Light[][] lights = new Light[100][100];
            Light[][] lights2 = new Light[100][100];
            int i = 0;
            while (scanner.hasNextLine()) {
                char[] c = scanner.nextLine().toCharArray();
                for (int j = 0; j < c.length; j++) {
                    lights[i][j] = LightFactory.createLight(c[j] == ON ? State.ON : State.OFF, null);
                    lights2[i][j] = LightFactory.createLight(c[j] == ON ? State.ON : State.OFF, null);
                }
                i++;
            }
            for (int j = 0; j < 100; j++) {
                lights = tick(false, lights);
                lights2 = tick(true, lights2);
            }
            return new TaskAnswer(Arrays.stream(lights).flatMap(Arrays::stream).filter(l -> l.getState() == State.ON).count(), Arrays.stream(lights2).flatMap(Arrays::stream).filter(l -> l.getState() == State.ON).count());
        }
    }

    private Light[][] tick(boolean part, Light[][] lights) {
        Light[][] buffer = new Light[100][100];
        if (part) {
            lights[0][99] = LightFactory.createLight(State.ON, null);
            lights[99][99] = LightFactory.createLight(State.ON, null);
            lights[0][0] = LightFactory.createLight(State.ON, null);
            lights[99][0] = LightFactory.createLight(State.ON, null);
        }
        for (int k = 0; k < 100; k++) {
            for (int l = 0; l < 100; l++) {
                int n = countNeighbors(k, l, lights);
                if (lights[k][l].getState() == State.ON && (n < 2 || n > 3)) buffer[k][l] = LightFactory.createLight(State.OFF, null);
                else {
                    if (n == 3) {
                        buffer[k][l] = LightFactory.createLight(State.ON, null);
                    } else {
                        buffer[k][l] = lights[k][l];
                    }
                }
            }
        }
        if (part) {
            buffer[0][99] = LightFactory.createLight(State.ON, null);
            buffer[99][99] = LightFactory.createLight(State.ON, null);
            buffer[0][0] = LightFactory.createLight(State.ON, null);
            buffer[99][0] = LightFactory.createLight(State.ON, null);
        }
        return buffer;
    }

    private int countNeighbors(int x, int y, Light[][] lights) {
        int count = 0;
        for (int i = x > 0 ? -1 : 0; i < (x < 99 ? 2 : 1); i++) {
            for (int j = y > 0 ? -1 : 0; j < (y < 99 ? 2 : 1); j++) {
                if (!(i == 0 && j == 0) && lights[x + i][y + j].getState() == State.ON) count++;
            }
        }
        return count;
    }
}
