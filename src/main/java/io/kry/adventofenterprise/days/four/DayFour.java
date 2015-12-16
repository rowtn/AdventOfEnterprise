package io.kry.adventofenterprise.days.four;

import io.kry.adventofenterprise.days.four.factories.MD5Factory;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.security.NoSuchAlgorithmException;

public class DayFour implements Day {

    public static final int ID = 4;

    @Override
    public TaskAnswer solve() throws TaskException {
        String key = "yzbqklnj";
        try {
            int i = -1;
            int first = -1, second = -1;
            long start = System.currentTimeMillis();
            while (first == -1) {
                byte[] hash = MD5Factory.hash(key + ++i);
                if (hash[0] == 0 && hash[1] == 0 && hash[2] >> 4 == 0) {
                    if (first == -1) first = i;
                }
            }
            System.out.println("finished in " + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            i = -1;
            while (second == -1) {
                byte[] hash = MD5Factory.hash(key + ++i);
                if (hash[0] == 0 && hash[1] == 0 && hash[2] == 0) {
                    if (second == -1) second = i;
                }
            }
            System.out.println("finished in " + (System.currentTimeMillis() - start) + "ms");
            return new TaskAnswer(first, second);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
