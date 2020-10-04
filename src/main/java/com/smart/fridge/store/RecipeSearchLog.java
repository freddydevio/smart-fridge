package com.smart.fridge.store;

import com.smart.fridge.search.RecipeSearchInput;
import com.smart.fridge.search.RecipeSearchOutput;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Setter
@Getter
@MongoEntity(collection="RecipeSearchLog")
public class RecipeSearchLog extends PanacheMongoEntity {
    public ObjectId id;
    private RecipeSearchInput input;
    private RecipeSearchOutput output;
    private LocalDateTime timestamp;
}
