package io.kry.adventofenterprise.days.fifteen.builders;

import io.kry.adventofenterprise.days.fifteen.object.CookieIngredient;

public class CookieIngredientBuilder {

    private int capacity, durability, flavour, texture, calories;
    private String name;

    public CookieIngredientBuilder(String name) {
        this.name = name;
    }

    public CookieIngredientBuilder capacity(String s) {
        this.capacity = Integer.parseInt(s);
        return this;
    }

    public CookieIngredientBuilder durability(String s) {
        this.durability = Integer.parseInt(s);
        return this;
    }

    public CookieIngredientBuilder flavour(String s) {
        this.flavour = Integer.parseInt(s);
        return this;
    }

    public CookieIngredientBuilder texture(String s) {
        this.texture = Integer.parseInt(s);
        return this;
    }

    public CookieIngredientBuilder calories(String s) {
        this.calories = Integer.parseInt(s);
        return this;
    }

    public CookieIngredient build() {
        return new CookieIngredient(name, capacity, durability, flavour, texture, calories);
    }
}
