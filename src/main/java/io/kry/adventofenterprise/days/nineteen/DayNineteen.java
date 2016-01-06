package io.kry.adventofenterprise.days.nineteen;

import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayNineteen implements Day {

    public static final int ID = 19;
    public static final Pattern PATTERN = Pattern.compile("([A-z]+) => ([A-z]+)");

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("19.input"))) {
            List<Replacement> replacements = new ArrayList<>();
            String medicine = "";
            while (scanner.hasNextLine()) {
                String in = scanner.nextLine();
                if (in.equals("")) {
                    medicine = scanner.nextLine();
                    break;
                }
                Matcher m = PATTERN.matcher(in);
                m.matches();
                replacements.add(new Replacement(m.group(1), m.group(2)));
            }
            List<String> newMolecules = new ArrayList<>();
            for (Replacement r : replacements) {
                String medicineClone = medicine;
                while (medicineClone.contains(r.getFrom())) {
                    newMolecules.add(medicineClone.replaceFirst(r.getFrom(), r.getTo()).replaceAll("™", r.getFrom()));
                    medicineClone = medicineClone.replaceFirst(r.getFrom(), "™");
                }
            }
            int count = 0;
            while(!medicine.equals("e")) {
                for (Replacement each : replacements) {
                    if (medicine.contains(each.getTo())) {
                        medicine = each.replaceLast(medicine);
                        count++;
                    }
                }
            }
            return new TaskAnswer(newMolecules.stream().distinct().count(), count);
        }
    }

    static class Replacement {
        private String from, to;

        public Replacement(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String replaceLast(String toReplace) {
            return toReplace.substring(0, toReplace.lastIndexOf(to)) + from + toReplace.substring(toReplace.lastIndexOf(to) + to.length());
        }
    }
}
