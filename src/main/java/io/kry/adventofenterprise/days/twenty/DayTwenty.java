package io.kry.adventofenterprise.days.twenty;

import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwenty implements Day {

    public static final int ID = 20;

    @Override
    public TaskAnswer solve() throws TaskException {
        final int input = 29000000;
        int ans = 0;
        int ans2 = 0;
        int[] houses = new int[input/10];
        int[] houses2 = new int[input/10];
        for (int i = 1; i < input/10; i++) {
            for (int j = i; j < input/10; j += i) {
                houses[j] += i * 10;
            }
            for (int j = i; (j <= i*50 && j < input/10); j += i) {
                houses2[j] += i * 11;
            }
        }
        for (int i = 0; ans == 0 || ans2 == 0; i++) {
            if (ans == 0 && houses[i] >= input) {
                ans = i;
            }
            if (ans2 == 0 && houses2[i] >= input) {
                ans2 = i;
            }
        }
        return new TaskAnswer(ans, ans2);
    }

    private int calcPresents(Integer integer) {
        return 0;
    }
}
