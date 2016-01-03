package io.kry.adventofenterprise.days.fifteen.object;

import io.kry.adventofenterprise.days.fifteen.builders.CookieIngredientBuilder;

public class CookieIngredient {

    private int capacity;
    private int durability;
    private int flavour;
    private int texture;
    private int calories;
    private String name;

    public CookieIngredient(String name, int capacity, int durability, int flavour, int texture, int calories) {
        this.capacity = capacity;
        this.durability = durability;
        this.flavour = flavour;
        this.texture = texture;
        this.calories = calories;
        this.name = name;
    }

    public static CookieIngredientBuilder builder(String name) {
        return new CookieIngredientBuilder(name);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDurability() {
        return durability;
    }

    public int getFlavour() {
        return flavour;
    }

    public int getTexture() {
        return texture;
    }

    public int getCalories() {
        return calories;
    }
}
