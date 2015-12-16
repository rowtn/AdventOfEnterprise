package io.kry.adventofenterprise.days.ten;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTen implements Day {

    public static final int ID = 10;

    @Override
    public TaskAnswer solve() throws TaskException {
        String input = "3113322113";
        for (int i = 0; i < 40; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : input.split("(?<=(\\d))(?!\\1)")) {
                stringBuilder.append(s.length()).append(s.substring(0, 1));
            }
            input = stringBuilder.toString();
        }
        int a = input.length();
        input = "3113322113";
        for (int i = 0; i < 50; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : input.split("(?<=(\\d))(?!\\1)")) {
                stringBuilder.append(s.length()).append(s.substring(0, 1));
            }
            input = stringBuilder.toString();
        }
        return new TaskAnswer(a, input.length());
    }
}

