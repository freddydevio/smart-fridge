package com.smart.fridge.service;

import com.smart.fridge.search.RecipeSearchInput;
import com.smart.fridge.search.RecipeSearchOutput;
import com.smart.fridge.store.RecipeSearchLog;

import javax.enterprise.context.Dependent;
import java.time.LocalDateTime;

@Dependent
public class RecipeSearchLogService {

    public void log(RecipeSearchInput searchInput, RecipeSearchOutput searchOutput) {
        RecipeSearchLog recipeSearchLog = new RecipeSearchLog();
        recipeSearchLog.setInput(searchInput);
        recipeSearchLog.setOutput(searchOutput);
        recipeSearchLog.setTimestamp(LocalDateTime.now());

        RecipeSearchLog.persist(recipeSearchLog);
    }
}
