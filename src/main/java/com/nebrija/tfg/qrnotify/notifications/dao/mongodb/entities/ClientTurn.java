package com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@SuperBuilder
public class ClientTurn extends AuditableEntity {

    @MongoId
    private ObjectId _id;

    @Field("client_id")
    private String clientId;
    @Field("turn_number")
    private String turnNumber;
    @Field("status")
    private String status;
}
