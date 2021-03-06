package io.kry.adventofenterprise.days.thirteen;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.kry.adventofenterprise.framework.Utils.*;

public class DayThirteen implements Day {

    public static final int ID = 13;
    private Map<String, Map<String, Integer>> happinessUnits = new HashMap<>();
    private Set<String> names = new HashSet<>();

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("13.input"))) {

            Pattern p = Pattern.compile("([A-z]+) would (lose|gain) (\\d+) happiness units by sitting next to ([A-z]+)\\.");
            while (scanner.hasNextLine()) {
                Matcher m = p.matcher(scanner.nextLine());
                m.matches();
                String personA = m.group(1);
                String action = m.group(2);
                String i = m.group(3);
                String personB = m.group(4);
                names.addAll(Arrays.asList(personA, personB));
                if (!happinessUnits.containsKey(personA)) happinessUnits.put(personA, new HashMap<>());
                happinessUnits.get(personA).put(personB, Integer.parseInt(i) * (action.equals("gain") ? 1 : -1));
            }

            int p1 = calculateHappiness();

            happinessUnits.put("rowtn", new HashMap<>());
            for (String name : names) {
                happinessUnits.get(name).put("rowtn", 0);
                happinessUnits.get("rowtn").put(name, 0);
            }
            names.add("rowtn");
            int p2 = calculateHappiness();

            return new TaskAnswer(p1, p2);
        }
    }

    private Integer calculateHappiness() {
        Set<Integer> allDeltaHappiness = permutations(names).stream().map(arrangment -> {
            int h = 0;
            for (int j = 0; j < arrangment.size() - 1; j++) {
                int delta;
                String personA = arrangment.get(j);
                String personB = arrangment.get(j + 1);
                delta = happinessUnits.get(personA).get(personB) + happinessUnits.get(personB).get(personA);
                h += delta;
            }
            h += happinessUnits.get(arrangment.get(0)).get(arrangment.get(arrangment.size() - 1)) + happinessUnits.get(arrangment.get(arrangment.size() - 1)).get(arrangment.get(0));
            return h;
        }).collect(Collectors.toSet());
        return allDeltaHappiness.stream().max(Integer::compare).get();
    }
}

