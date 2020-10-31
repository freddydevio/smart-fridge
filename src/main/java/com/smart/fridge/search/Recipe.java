package com.smart.fridge.search;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Recipe {

    private int recipeId;
    private String title;
    private String imageUrl;
    private ArrayList missedIngredients;
    private ArrayList usedIngredients;
    private int usedIngredientCount;
    private int missedIngredientCount;
    private int likes;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private String sourceUrl;
    private String summary;
    private int readyInMinutes;
    private String spoonacularSourceUrl;
    private ArrayList analyzedInstructions;
    private ArrayList extendedIngredients;
}
