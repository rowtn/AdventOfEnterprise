package io.kry.adventofenterprise.days.sixteen.object;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aunty {

    private static final Pattern AUNTY_ID = Pattern.compile("Sue (\\d+).*");
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
                if (!Objects.equals(details.get(s), b.details.get(s))) return false;
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aunty) {
            Aunty b = (Aunty) obj;
            for (String s : b.details.keySet()) {
                switch (s.toLowerCase()) {
                    case "cats":
                    case "trees":
                        if (!(details.get(s) < b.details.get(s))) return false;
                        break;
                    case "pomeranians":
                    case "goldfish":
                        if (!(details.get(s) > b.details.get(s))) return false;
                        break;
                    default:
                        if (!Objects.equals(details.get(s), b.details.get(s))) return false;
                        break;
                }
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