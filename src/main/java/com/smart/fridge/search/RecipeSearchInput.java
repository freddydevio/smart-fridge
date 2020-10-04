package com.smart.fridge.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeSearchInput {
    private List<String> ingredients;
    private int maxRecipes = 5;
    private boolean onlyPublic = true;
    private boolean ignorePantry = false;
    private String apiKey;
}
