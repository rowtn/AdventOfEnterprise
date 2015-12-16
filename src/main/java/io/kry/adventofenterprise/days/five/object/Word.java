package io.kry.adventofenterprise.days.five.object;

import java.util.regex.Pattern;

public class Word {

    private static final Pattern REPEAT = Pattern.compile("(\\w)\\1+");
    private static final Pattern VOWEL = Pattern.compile("(?i)(?:[a-z]*[aeiou]){3}[a-z]*");
    private static final Pattern BANNED = Pattern.compile("(?=.*(pq|ab|cd|xy))");

    private static final Pattern REPEAT_NO_OVERLAP = Pattern.compile(".*(.).\\1.*");
    private static final Pattern SANDWICHED = Pattern.compile(".*(..).*\\1.*");

    private String word;

    public Word(String word) {
        this.word = word;
    }

    public boolean previouslyNaughty() {
        return REPEAT.matcher(word).find() && VOWEL.matcher(word).find() && !BANNED.matcher(word).find();
    }

    public boolean currentlyNaughty() {
        return REPEAT_NO_OVERLAP.matcher(word).find() && SANDWICHED.matcher(word).find();
    }
}
