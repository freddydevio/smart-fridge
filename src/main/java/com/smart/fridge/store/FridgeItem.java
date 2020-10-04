
package com.smart.fridge.store;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MongoEntity(collection="Item")
@Setter
public class FridgeItem extends PanacheMongoEntity {
    public ObjectId id;
    public String name;
    @JsonbDateFormat("yyyy-MM-dd")
    public LocalDate expireDate;
    @JsonbDateFormat("yyyy-MM-dd H:m:s")
    public LocalDateTime addedDateTime;
}
