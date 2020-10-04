package com.smart.fridge.service;

import com.google.gson.*;
import com.smart.fridge.gateway.RecipeSearchGateway;
import com.smart.fridge.search.Recipe;
import com.smart.fridge.search.RecipeSearchInput;
import com.smart.fridge.search.RecipeSearchOutput;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;

@Dependent
public class RecipeSearchService {

    @Inject
    @RestClient
    RecipeSearchGateway recipeSearchGateway;

    @Inject
    RecipeSearchLogService searchLogService;

    public RecipeSearchOutput search(RecipeSearchInput searchInput) {
        RecipeSearchOutput searchOutput = new RecipeSearchOutput();

        JsonArray recipeList = JsonParser.parseString(recipeSearchGateway.getByIngredients(
            String.join(",", searchInput.getIngredients()),
            searchInput.getMaxRecipes(),
            searchInput.isOnlyPublic(),
            searchInput.isIgnorePantry(),
            searchInput.getApiKey()
        )).getAsJsonArray();

        for (JsonElement recipeEntry : recipeList) {
            JsonObject recipeEntryJson = recipeEntry.getAsJsonObject();

            Recipe recipe = new Recipe();
            recipe.setRecipeId(recipeEntryJson.get("id").getAsInt());
            recipe.setTitle(recipeEntryJson.get("title").getAsString());
            recipe.setImageUrl(recipeEntryJson.get("image").getAsString());
            recipe.setMissedIngredients(new Gson().fromJson(recipeEntryJson.get("missedIngredients"), ArrayList.class));
            recipe.setUsedIngredients(new Gson().fromJson(recipeEntryJson.get("usedIngredients"), ArrayList.class));
            recipe.setLikes(recipeEntryJson.get("likes").getAsInt());
            recipe.setUsedIngredientCount(recipeEntryJson.get("usedIngredientCount").getAsInt());
            recipe.setMissedIngredientCount(recipeEntryJson.get("missedIngredientCount").getAsInt());

            this.getRecipeInformation(recipe, searchInput);

            searchOutput.addRecipe(recipe);
        }

        searchLogService.log(searchInput, searchOutput);

        return searchOutput;
    }

    private void getRecipeInformation(Recipe recipe, RecipeSearchInput searchInput) {
        JsonObject recipeInformation = JsonParser.parseString(
                recipeSearchGateway.getRecipeInformation(
                        String.valueOf(recipe.getRecipeId()),
                        searchInput.getApiKey()
                )).getAsJsonObject();

        recipe.setGlutenFree(recipeInformation.get("glutenFree").getAsBoolean());
        recipe.setDairyFree(recipeInformation.get("dairyFree").getAsBoolean());
        recipe.setVegan(recipeInformation.get("vegan").getAsBoolean());
        recipe.setVegetarian(recipeInformation.get("vegetarian").getAsBoolean());
        recipe.setReadyInMinutes(recipeInformation.get("readyInMinutes").getAsInt());
        recipe.setSourceUrl(recipeInformation.get("sourceUrl").getAsString());
        recipe.setSummary(recipeInformation.get("summary").getAsString());
        recipe.setSpoonacularSourceUrl(recipeInformation.get("spoonacularSourceUrl").getAsString());
    }
}
