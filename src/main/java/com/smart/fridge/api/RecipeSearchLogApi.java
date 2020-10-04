package com.smart.fridge.api;
import com.smart.fridge.store.RecipeSearchLog;
import org.bson.types.ObjectId;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/recipe-search-log")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeSearchLogApi {

    @GET
    public List<RecipeSearchLog> getAll() {
        return RecipeSearchLog.listAll();
    }

    @GET
    @Path("/{id}")
    public RecipeSearchLog getOne(@PathParam("id") String id) {
        return RecipeSearchLog.findById(new ObjectId(id));
    }
}
