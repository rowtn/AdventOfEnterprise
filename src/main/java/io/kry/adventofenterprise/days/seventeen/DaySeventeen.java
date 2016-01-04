package io.kry.adventofenterprise.days.seventeen;

import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static io.kry.adventofenterprise.framework.Utils.combinations;

//I could make this fast, but that gloriously stupid one-liner is too beautiful to discard
public class DaySeventeen implements Day {

    public static final int ID = 17;
    private static final int totalEggnog = 150;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("17.input"))) {
            List<Integer> buckets = new ArrayList<>();
            while (scanner.hasNextLine()) {
                buckets.add(Integer.parseInt(scanner.nextLine()));
            }
            List<List<Integer>> all = new ArrayList<>();
            for (int size = buckets.size(); size > 0; size--) {
                all.addAll(combinations(buckets, size));
            }
            return new TaskAnswer(all.stream().filter(list -> list.stream().mapToInt(v -> v).sum() == totalEggnog).count(), all.stream().filter(l -> l.stream().mapToInt(i -> i).sum() == totalEggnog).filter(l -> l.size() == all.stream().filter(k -> k.stream().mapToInt(i -> i).sum() == totalEggnog).mapToInt(List::size).min().getAsInt()).count());
        }
    }

    private void printlist(List<Integer> integers) {
        System.out.println(StringUtils.join(integers, ", "));
    }


}
