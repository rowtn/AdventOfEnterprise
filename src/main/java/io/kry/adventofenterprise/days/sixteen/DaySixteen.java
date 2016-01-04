package io.kry.adventofenterprise.days.sixteen;


import io.kry.adventofenterprise.days.sixteen.object.Aunty;
import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.*;

public class DaySixteen implements Day {

    public static final int ID = 16;

    @Override
    public TaskAnswer solve() throws TaskException {
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("16.input"))) {
            List<Aunty> aunties = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                aunties.add(Aunty.fromString(input));
            }
            final Aunty gifter = Aunty.fromString("Sue 0: children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0, goldfish: 5, trees: 3, cars: 2, perfumes: 1");
            return new TaskAnswer(aunties.stream().filter(gifter::faultyEquals).findFirst().get().getId(), aunties.stream().filter(gifter::equals).findFirst().get().getId());
        }
    }
}
