package io.kry.adventofenterprise.days.fifteen;

import io.kry.adventofenterprise.days.fifteen.builders.CookieBuilder;
import io.kry.adventofenterprise.days.fifteen.object.Cookie;
import io.kry.adventofenterprise.days.fifteen.object.CookieIngredient;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//I hate this day with a passion
public class DayFifteen implements Day {

    public static final int ID = 15;
    private static List<CookieIngredient> ingredients = new ArrayList<>();

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("15.input"))) {
            Pattern p = Pattern.compile("([A-z]+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");
            while (scanner.hasNextLine()) {
                String in = scanner.nextLine();
                Matcher m = p.matcher(in);
                m.matches();
                ingredients.add(CookieIngredient.builder(m.group(1)).capacity(m.group(2)).durability(m.group(3)).flavour(m.group(4)).texture(m.group(5)).calories(m.group(6)).build());
            }

            List<List<Integer>> combinations = new ArrayList<>();
            List<Integer> counts = ingredients.stream().map(i -> 0).collect(Collectors.toList());
            combinations(100, 0, combinations, counts);
            int one = combinations.stream().map(DayFifteen::cookieFromList).mapToInt(c -> c.getScore(0)).max().getAsInt();
            combinations(100, 0, combinations, counts);
            int two = combinations.stream().map(DayFifteen::cookieFromList).mapToInt(c -> c.getScore(500)).max().getAsInt();
            return new TaskAnswer(one, two);
        }
    }

    private static Cookie cookieFromList(List<Integer> list) {
        CookieBuilder b = Cookie.builder();
        for (int i = 0; i < list.size(); i++) {
            b.ingredient(ingredients.get(i), list.get(i));
        }
        return b.build();
    }

    public void combinations(int target, int index, List<List<Integer>> results, List<Integer> list) {
        for(int i = 0;i <= target;i++) {
            List<Integer> newList = new ArrayList<>(list);
            newList.set(index, i);

            if(index < list.size() - 1 && newList.subList(0, index).stream().collect(Collectors.summingInt(v -> v)) <= target) {
                combinations(target, index + 1, results, newList);
            }
            if(index == list.size() - 1 && newList.stream().collect(Collectors.summingInt(v -> v)) == target) {
                results.add(newList);
            }
        }
    }

}
