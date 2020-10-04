package com.smart.fridge.api;

import com.smart.fridge.store.FridgeItem;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/api/fridge-store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FridgeStoreApi {

    @GET
    public List<FridgeItem> getAll() {
        return FridgeItem.listAll();
    }

    @GET
    @Path("/{id}")
    public FridgeItem getOne(@PathParam("id") String id) {
        return FridgeItem.findById(new ObjectId(id));
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") String id) {
        FridgeItem.deleteById(new ObjectId(id));

        return Response.ok().build();
    }

    @POST
    public Response create(FridgeItem fridgeItem) {
        fridgeItem.setAddedDateTime(LocalDateTime.now());
        FridgeItem.persist(fridgeItem);

        return Response.ok().build();
    }
}
