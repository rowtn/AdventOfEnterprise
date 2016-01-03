package io.kry.adventofenterprise.days.sixteen;


import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaySixteen implements Day {

    public static final int ID = 16;
    private static final Pattern AUNTY_ID = Pattern.compile("Sue (\\d+).*");

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("16.input"))) {
            List<Aunty> aunties = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                aunties.add(Aunty.fromString(input));
            }
            Aunty gifter = Aunty.fromString("Sue 0: children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0, goldfish: 5, trees: 3, cars: 2, perfumes: 1");
            for (Aunty aunty : aunties) {
                if (gifter.faultyEquals(aunty)) gifter = aunty;
            }
            return new TaskAnswer(gifter.getId(), 0);
        }
    }

    static class Aunty {
        private Map<String, Integer> details = new HashMap<>();
        private int id;

        public Aunty(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void addDetail(String key, int val)  {
            details.put(key, val);
        }

        public boolean faultyEquals(Object obj) {
            if (obj instanceof Aunty) {
                Aunty b = (Aunty) obj;
                for (String s : b.details.keySet()) {
                    if (details.get(s) != b.details.get(s)) return false;
                }
                return true;
            }
            return super.equals(obj);
        }

        public static Aunty fromString(String input) {
            Matcher m = AUNTY_ID.matcher(input);
            m.matches();
            Aunty aunty = new Aunty(Integer.parseInt(m.group(1)));
            for (String rawDetail : input.replaceAll("Sue \\d+: ", "").split(", ")) {
                String[] parts = rawDetail.split(": ");
                aunty.addDetail(
                        parts[0],
                        Integer.parseInt(parts[1]));
            }
            return aunty;
        }
    }
}
