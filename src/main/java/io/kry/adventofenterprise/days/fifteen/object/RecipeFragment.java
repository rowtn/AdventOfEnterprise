package io.kry.adventofenterprise.days.fifteen.object;

import java.util.Map;

public class RecipeFragment {

    private CookieIngredient ingredient;
    private int amount;

    public RecipeFragment(CookieIngredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Map.Entry<CookieIngredient, Integer> getFragmentAsEntry() {
        return new Map.Entry<CookieIngredient, Integer>() {
            @Override
            public CookieIngredient getKey() {
                return RecipeFragment.this.ingredient;
            }

            @Override
            public Integer getValue() {
                return RecipeFragment.this.amount;
            }

            @Override
            public Integer setValue(Integer value) {
                return null;
            }
        };
    }
}
