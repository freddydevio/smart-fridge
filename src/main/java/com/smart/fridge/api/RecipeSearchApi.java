package com.smart.fridge.api;

import com.smart.fridge.search.RecipeSearchInput;
import com.smart.fridge.search.RecipeSearchOutput;
import com.smart.fridge.service.RecipeSearchService;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @POST
    @Path("/{id}")
    public RecipeSearchOutput search(@PathParam("id") int id, RecipeSearchInput searchInput) {
        return this.searchService.searchDetail(id, searchInput);
    }
}
