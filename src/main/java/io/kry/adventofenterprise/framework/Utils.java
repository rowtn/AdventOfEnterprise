package io.kry.adventofenterprise.framework;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static <T> List<List<T>> combinations(List<T> original, int k) {
        List<List<T>> all = new ArrayList<>();
        int r = 0, index = 0, n = original.size();
        List<Integer> combination = new ArrayList<>(Collections.nCopies(k, 0));
        while (r >= 0) {
            if (index <= n + r - k) {
                combination.set(r, index);
                if (r == k - 1) {
                    //all.add(original.stream().filter(t -> combination.contains(original.indexOf(t))).collect(Collectors.toList()));
                    List<T> next = new ArrayList<>();
                    for (Integer i : combination) {
                        next.add(original.get(i));
                    }
                    all.add(next);
                    index++;
                } else {
                    index = combination.get(r) + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0) {
                    index = combination.get(r) + 1;
                } else {
                    index = combination.get(0) + 1;
                }
            }
        }
        return all;
    }
}
