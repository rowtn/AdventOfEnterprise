package io.kry.adventofenterprise.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {

    public static <T> List<List<T>> permutations(Collection<T> names) {
        List<List<T>> permutations = new ArrayList<>();

        permutations(new ArrayList<>(), new ArrayList<>(names), permutations);

        return permutations;
    }

    public static <T> void permutations(List<T> head, List<T> tail, List<List<T>> permutations) {
        if(tail.size() == 0) {
            permutations.add(head);
            return;
        }
        for(int i = 0;i < tail.size();i++) {
            List<T> newTail = new ArrayList<>(tail);
            List<T> newHead = new ArrayList<>(head.size() + 1);

            newHead.addAll(head);
            newHead.add(newTail.remove(i));

            permutations(newHead, newTail, permutations);
        }
    }
}
