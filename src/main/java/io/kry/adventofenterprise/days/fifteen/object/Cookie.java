package io.kry.adventofenterprise.days.fifteen.object;

import io.kry.adventofenterprise.days.fifteen.builders.CookieBuilder;

import java.util.Arrays;

public class Cookie {

    public RecipeFragment[] recipe;

    //it's either private fields or a final int[] in the getScore method.
    private int capacity;
    private int durability;
    private int flavour;
    private int texture;
    private int calories;

    public Cookie(RecipeFragment[] fragments) {
        this.recipe = fragments;
    }

    public int getScore(int requiredCalories) {
        capacity = 0;
        durability = 0;
        flavour = 0;
        texture = 0;
        calories = 0;
        Arrays.asList(recipe).stream().map(RecipeFragment::getFragmentAsEntry).forEach(i -> {
            capacity += i.getKey().getCapacity() * i.getValue();
            durability += i.getKey().getDurability() * i.getValue();
            flavour += i.getKey().getFlavour() * i.getValue();
            texture += i.getKey().getTexture() * i.getValue();
            calories += i.getKey().getCalories() * i.getValue();
        });
        if (capacity <= 0 || durability <= 0 || flavour <= 0 || texture <= 0) return 0;
        if (requiredCalories > 0 && calories != requiredCalories) return 0;
        return capacity * durability * flavour * texture;
    }

    public static CookieBuilder builder() {
        return new CookieBuilder();
    }

}
