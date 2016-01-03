package io.kry.adventofenterprise.days.fifteen.builders;

import io.kry.adventofenterprise.days.fifteen.object.Cookie;
import io.kry.adventofenterprise.days.fifteen.object.CookieIngredient;
import io.kry.adventofenterprise.days.fifteen.object.RecipeFragment;

import java.util.ArrayList;
import java.util.List;

public class CookieBuilder {

    private List<RecipeFragment> fragments = new ArrayList<>();

    public CookieBuilder ingredient(RecipeFragment f) {
        fragments.add(f);
        return this;
    }

    public CookieBuilder ingredient(CookieIngredient i, int amt) {
        return ingredient(new RecipeFragment(i, amt));
    }

    public Cookie build() {
        return new Cookie(fragments.toArray(new RecipeFragment[fragments.size()]));
    }
}
