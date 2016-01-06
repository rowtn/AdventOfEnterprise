package io.kry.adventofenterprise.days.twentyone;

import io.kry.adventofenterprise.exceptions.TaskException;
import io.kry.adventofenterprise.framework.Day;
import io.kry.adventofenterprise.framework.TaskAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwentyOne implements Day {

    public static final int ID = 21;
    private static final Pattern gearPattern = Pattern.compile("([A-z]+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");

    @Override
    public TaskAnswer solve() throws TaskException {
        List<Weapon> weapons = new ArrayList<>();
        List<Armour> armours = new ArrayList<>();
        List<Ring> rings = new ArrayList<>();
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("21.weapons.shop.input"))) {
            while (scanner.hasNextLine()) weapons.add(new Weapon(scanner.nextLine()));
        }
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("21.armour.shop.input"))) {
            while (scanner.hasNextLine()) armours.add(new Armour(scanner.nextLine()));
        }
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("21.rings.shop.input"))) {
            while (scanner.hasNextLine()) rings.add(new Ring(scanner.nextLine()));
        }
        Contestant boss = new Contestant(8, 2, 109, 0, 0, 0);
        List<Contestant> playerCombinations = new ArrayList<>();
        for (Weapon weapon : weapons) {
            for (Armour armour : armours) {
                for (Ring ring : rings) {
                    for (Ring ring1 : rings) {
                        if (ring1.name.equals(ring.name)) continue;
                        playerCombinations.add(new Contestant(weapon.damage, armour.prot, 100, weapon.cost + armour.cost + ring.cost + ring1.cost, ring.eff, ring1.eff)); //yikes!
                    }
                }
            }
        }
        return new TaskAnswer(playerCombinations.stream().filter(player -> player.beats(boss)).mapToInt(Contestant::getCost).min().getAsInt(), playerCombinations.stream().filter(boss::beats).mapToInt(Contestant::getCost).max().getAsInt());
    }

    static class Contestant {
        private int damage, prot, health, cost;

        public Contestant(int damage, int prot, int health, int cost, int ringA, int ringB) {
            this.damage = damage;
            this.prot = prot;
            this.health = health;
            this.cost = cost;
            if (ringA > 0) this.damage += ringA;
            else this.prot -= ringA;
            if (ringB > 0) this.damage += ringB;
            else this.prot -= ringB;
        }

        public boolean beats(Contestant b) {
            int damageA = damage - b.prot;
            int damageB = b.damage - prot;
            damageA = Math.max(1, damageA);
            damageB = Math.max(1, damageB);
            return b.health/damageA < health/damageB;
        }

        public int getCost() {
            return cost;
        }
    }

    static class Weapon {
        String name;
        int damage, cost;

        public Weapon(String s) {
            Matcher m = gearPattern.matcher(s);
            m.matches();
            name = m.group(1);
            cost = Integer.parseInt(m.group(2));
            damage = Integer.parseInt(m.group(3));
        }
    }

    static class Armour {
        String name;
        int prot, cost;

        public Armour(String s) {
            Matcher m = gearPattern.matcher(s);
            m.matches();
            name = m.group(1);
            cost = Integer.parseInt(m.group(2));
            prot = Integer.parseInt(m.group(4));
        }
    }

    static class Ring {
        String name;
        int eff, cost;

        public Ring(String s) {
            Matcher m = gearPattern.matcher(s);
            m.matches();
            name = m.group(1);
            cost = Integer.parseInt(m.group(2));
            eff = Integer.parseInt(m.group(3)) - Integer.parseInt(m.group(4));
        }
    }
}
