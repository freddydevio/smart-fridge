package com.smart.fridge.api;

import com.smart.fridge.search.RecipeSearchInput;
import com.smart.fridge.search.RecipeSearchOutput;
import com.smart.fridge.service.RecipeSearchService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/recipe-search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeSearchApi {

    @Inject
    RecipeSearchService searchService;

    @POST
    public RecipeSearchOutput search(RecipeSearchInput searchInput) {
        return this.searchService.search(searchInput);
    }
}
