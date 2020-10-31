package com.smart.fridge.gateway;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
@Produces("application/json")
public interface FoodGateway {
    @GET
    @Path("/findByIngredients")
    String getByIngredients(
        @QueryParam("ingredients") String ingredients,
        @QueryParam("number") int maxRecipes,
        @QueryParam("limitLicense") boolean onlyPublic,
        @QueryParam("ignorePantry") boolean ignorePantry,
        @QueryParam("apiKey") String apiKey
    );

    @GET
    @Path("/{id}/information")
    String getRecipeInformation(
        @PathParam("id") String id,
        @QueryParam("apiKey") String apiKey
    );
}
