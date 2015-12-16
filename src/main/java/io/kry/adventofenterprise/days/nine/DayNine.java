package io.kry.adventofenterprise.days.nine;

import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;
import io.kry.adventofenterprise.exceptions.TaskException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * enterprising is getting tiring now, nty
 */
public class DayNine implements Day {
    public static final int ID = 9;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("9.input"))) {
            Set<String> locations = new HashSet<>();
            Map<String, Map<String, Integer>> distances = new HashMap<>();
            Pattern cityNames = Pattern.compile("([A-z]+) to ([A-z]+) = (\\d+)");
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                Matcher m = cityNames.matcher(str);
                m.matches();
                locations.add(m.group(1));
                locations.add(m.group(2));
                if(!distances.containsKey(m.group(1))) {
                    distances.put(m.group(1), new HashMap<>());
                }
                distances.get(m.group(1)).put(m.group(2), Integer.parseInt(m.group(3)));
            }
            Set<Integer> allRoutes = permutations(locations).stream().map(cities -> {
                int dist = 0;
                for (int i = 0; i < cities.size() - 1; i++) {
                    String start = cities.get(i);
                    String end = cities.get(i + 1);
                    if(distances.containsKey(start) && distances.get(start).containsKey(end)) {
                        dist += distances.get(start).get(end);
                    } else {
                        dist += distances.get(end).get(start);
                    }
                }
                return dist;
            }).collect(Collectors.toSet());
            return new TaskAnswer(allRoutes.stream().min(Integer::compare).get(), allRoutes.stream().max(Integer::compare).get());
        }
    }

    private List<List<String>> permutations(Collection<String> names) {
        List<List<String>> permutations = new ArrayList<>();

        permutations(new ArrayList<>(), new ArrayList<>(names), permutations);

        return permutations;
    }

    private void permutations(List<String> head, List<String> tail, List<List<String>> permutations) {
        if(tail.size() == 0) {
            permutations.add(head);
            return;
        }
        for(int i = 0;i < tail.size();i++) {
            List<String> newHead = new ArrayList<>(head.size() + 1);
            List<String> newTail = new ArrayList<>(tail);

            newHead.addAll(head);
            newHead.add(newTail.remove(i));

            permutations(newHead, newTail, permutations);
        }
    }

}

