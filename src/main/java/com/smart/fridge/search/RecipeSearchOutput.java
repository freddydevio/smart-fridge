package com.smart.fridge.search;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class RecipeSearchOutput {

    private List<Recipe> recipeList;

    public RecipeSearchOutput() {
        this.recipeList = new ArrayList<>();
    }

    public RecipeSearchOutput(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void addRecipe(Recipe recipe) {
        this.recipeList.add(recipe);
    }
}
