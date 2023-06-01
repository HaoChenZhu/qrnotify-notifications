package com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@SuperBuilder
@Document(collection = "turns")
public class Turn extends AuditableEntity {

    @MongoId
    private ObjectId id;

    @Field("topic_id")
    private String topicId;

    @Field("name")
    private String name;

    @Field("publish_name")
    private String publishName;

    @Field("estimated_time")
    private int estimatedTime;

    @Field("current_turn")
    private String currentTurn;

    @Field("client_turn_list")
    private List<ClientTurn> clientTurnList;

    @Field("next_turn")
    private AtomicInteger nextTurnNumber;

    public Turn() {
        nextTurnNumber = new AtomicInteger(1);
    }

    public int getNextTurnNumber() {
        return nextTurnNumber.getAndIncrement();
    }

}
