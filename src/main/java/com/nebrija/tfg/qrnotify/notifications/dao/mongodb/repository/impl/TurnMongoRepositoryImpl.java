package com.nebrija.tfg.qrnotify.notifications.dao.mongodb.repository.impl;

import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.Turn;
import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.repository.TurnMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class TurnMongoRepositoryImpl implements TurnMongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Turn findBy_Id(String _id) {
        ObjectId id = new ObjectId(_id);
        Turn turn = mongoTemplate.findById(id, Turn.class);
        return turn;
    }

    @Override
    public void save(Turn turn) {
        mongoTemplate.save(turn);
    }

    @Override
    public List<Turn> findAll() {
        //find all turns from today

        List<Turn> turns = mongoTemplate.findAll(Turn.class);
        return turns;
    }

    @Override
    public List<Turn> findByCreatedDate() {
        LocalDate currentDate = LocalDate.now();

        // Convertir la fecha actual a un objeto Date de Java
        Date today = java.sql.Date.valueOf(currentDate);

        // Construir la consulta
        Query query = new Query(Criteria.where("createdDate").gte(currentDate.atStartOfDay()).lt(currentDate.plusDays(1).atStartOfDay()));

        return mongoTemplate.find(query, Turn.class);
    }

    @Override
    public Turn findByCreatedBy(String createdBy) {
        Query query = new Query(Criteria.where("createdBy").is(createdBy));
        return mongoTemplate.findOne(query, Turn.class);
    }

    @Override
    public boolean existsTurnToDayAndUser(String createdBy) {
        LocalDate currentDate = LocalDate.now();
        Query query = new Query(Criteria.where("createdBy").is(createdBy).and("createdDate").gte(currentDate.atStartOfDay()).lt(currentDate.plusDays(1).atStartOfDay()));
        return mongoTemplate.exists(query, Turn.class);
    }
}
