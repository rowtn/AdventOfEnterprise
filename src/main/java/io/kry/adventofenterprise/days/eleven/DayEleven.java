package io.kry.adventofenterprise.days.eleven;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEleven implements Day {

    public static final int ID = 11;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final Pattern RULE_ONE = Pattern.compile("(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)");
    private static final Pattern RULE_TWO = Pattern.compile("([iol])");

    @Override
    public TaskAnswer solve() throws TaskException {
        String input = "vzbxkghb";
        while (!validPassword(input)) input = next(input);
        String pw1 = input;
        do {
            input = next(input);
        } while (!validPassword(input));
        return new TaskAnswer(pw1, input);
    }

    private boolean validPassword(String pw) {
        Matcher m = RULE_ONE.matcher(pw);
        Matcher m1 = RULE_TWO.matcher(pw);
        m.matches();
        m1.matches();
        return m.find() && (!m1.find()) && (pw.length() - pw.replaceAll("([a-z])\\1{1}", "").length() >= 4);
    }

    private String next(String input) {
        char[] in = input.toCharArray();
        int i = in.length - 1;
        in[i] = next(in[i]);
        while (i > 0 && in[i] == 'z') {
            i--;
            in[i] = next(in[i]);
        }
        return new String(in);
    }

    private char next(char c) {
        return LETTERS.charAt((1 + LETTERS.indexOf(c)) % LETTERS.length());
    }
}

