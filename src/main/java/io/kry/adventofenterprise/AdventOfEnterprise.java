package io.kry.adventofenterprise;

import io.kry.adventofenterprise.framework.ClassGetter;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventOfEnterprise {

    private Map<String, Day> days;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        new AdventOfEnterprise();
    }

    public AdventOfEnterprise() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        days = new HashMap<>();
        for (Class<?> clazz : ClassGetter.getClassesForPackage(this, "io.kry.adventofenterprise.days")) {
            if (Day.class.isAssignableFrom(clazz)) {
                //days.put(String.valueOf(clazz.getField("ID").get(null)), (Day) clazz.getConstructor(File.class).newInstance(new File(clazz.getClassLoader().getResource(String.valueOf(clazz.getField("ID").get(null)) + ".input").getFile())));
                days.put(String.valueOf(clazz.getField("ID").get(null)), (Day) clazz.newInstance());
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) return;
            if (input.equalsIgnoreCase("list"))
                System.out.println("Available commands" + StringUtils.join(days.keySet(), ", ") + "list, exit.");
            if (days.containsKey(input)) {
                try {
                    TaskAnswer answer = days.get(input).solve();
                    System.out.println(String.format("The answer to part one of the task is %s and the answer to part two is %s", answer.getPartOneAnswer(), answer.getPartTwoAnswer()));
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Could not find task...");
            }
        }
    }

}
