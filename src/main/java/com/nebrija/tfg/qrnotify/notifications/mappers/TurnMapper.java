package com.nebrija.tfg.qrnotify.notifications.mappers;

import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.ClientTurn;
import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.Turn;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiClientTurnResponseDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiTurnRequestDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiTurnResponseDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurnMapper {
    @Mapping(target = "topic", source = "publishName")
    @Mapping(expression = "java(objectIdToString(turn.getId()))", target = "id")
    @Mapping(target = "turns",source = "clientTurnList")
    ApiTurnResponseDto toDto(Turn turn);

    Turn toEntity(ApiTurnRequestDto apiTurnRequestDto);

    List<ApiTurnResponseDto> fromEntitiesToDtos(List<Turn> turns);

    ApiClientTurnResponseDto toDtoClient(ClientTurn clientTurn);

    List<ApiClientTurnResponseDto> toDtosClients(List<ClientTurn> turns);

    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

}
