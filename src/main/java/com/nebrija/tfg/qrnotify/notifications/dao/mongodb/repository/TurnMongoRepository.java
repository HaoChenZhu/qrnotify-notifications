package com.nebrija.tfg.qrnotify.notifications.dao.mongodb.repository;

import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.Turn;

import java.util.Date;
import java.util.List;

public interface TurnMongoRepository {
    Turn findBy_Id(String _id);

    void save(Turn turn);

    List<Turn> findAll();

    List<Turn> findByCreatedDate();

    Turn findByCreatedBy(String createdBy);




}
