package com.nebrija.tfg.qrnotify.notifications.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebrija.tfg.qrnotify.notifications.clients.impl.AdminClientImpl;
import com.nebrija.tfg.qrnotify.notifications.clients.impl.TopicClientImpl;
import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.ClientTurn;
import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.entities.Turn;
import com.nebrija.tfg.qrnotify.notifications.dao.mongodb.repository.TurnMongoRepository;
import com.nebrija.tfg.qrnotify.notifications.exceptions.ApiResourceNotFoundException;
import com.nebrija.tfg.qrnotify.notifications.exceptions.ServerErrorException;
import com.nebrija.tfg.qrnotify.notifications.mappers.TurnMapper;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiClientTurnResponseDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiTurnResponseDto;
import com.nebrija.tfg.qrnotify.notifications.models.ApiUserResponseDto;
import com.nebrija.tfg.qrnotify.notifications.services.AuthUserService;
import com.nebrija.tfg.qrnotify.notifications.services.MqttService;
import com.nebrija.tfg.qrnotify.notifications.services.NotificationPublisher;
import com.nebrija.tfg.qrnotify.notifications.services.SmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import com.nebrija.tfg.qrnotify.notifications.constants.*;

import static com.nebrija.tfg.qrnotify.notifications.constants.Constants.TURN_MESSAGE;
import static com.nebrija.tfg.qrnotify.notifications.constants.Constants.TURN_MESSAGE_2;

@Service
@Slf4j
public class NotificationPublisherImpl implements NotificationPublisher {

    @Autowired
    private TurnMongoRepository turnMongoRepository;

    @Autowired
    private TurnMapper turnMapper;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private TopicClientImpl topicClientImpl;

    @Autowired
    private AdminClientImpl adminClientImpl;

    @Autowired
    private MqttService mqttService;

    @Autowired
    private SmService smService;

    @Override
    public ApiTurnResponseDto activateTurn() {
        try {
            if (turnMongoRepository.existsTurnToDayAndUser(authUserService.getCurrentUser()) != null) {
                throw new IllegalArgumentException("Ya existe un turno activo para el usuario actual");
            }
            ApiTopicResponseDto apiTopicResponseDto = topicClientImpl.getTopicByOwner(authUserService.getCurrentUser());
            Turn turn = Turn.builder()
                    .name(apiTopicResponseDto.getName())
                    .topicId(apiTopicResponseDto.getId())
                    .publishName(apiTopicResponseDto.getPublishName())
                    .estimatedTime(0)
                    .clientTurnList(Collections.emptyList())
                    .build();
            turnMongoRepository.save(turn);
            ApiTurnResponseDto apiTurnResponseDto = turnMapper.toDto(turn);
            return apiTurnResponseDto;
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public ApiTurnResponseDto passTurn() {
        String admin = authUserService.getCurrentUser();
        Turn turn = turnMongoRepository.existsTurnToDayAndUser(admin);

        if (turn == null) {
            throw new ApiResourceNotFoundException("No existe turno activo");
        }

        List<ClientTurn> clientTurnList = new ArrayList<>(turn.getClientTurnList());

        if (clientTurnList.isEmpty()) {
            throw new ApiResourceNotFoundException("No hay clientes en la cola");
        }

        ClientTurn currentClientTurn = findCurrentClientTurn(clientTurnList, turn.getCurrentTurn());

        if (currentClientTurn != null) {
            updateTurnStatusAndSaveInList(clientTurnList, currentClientTurn, Constants.STATUS.COMPLETADO.toString());
        }

        ClientTurn nextClientTurn = findNextClientTurn(clientTurnList);

        if (nextClientTurn != null) {
            updateTurnStatusAndSaveInList(clientTurnList, nextClientTurn, Constants.STATUS.EN_CURSO.toString());
            turn.setCurrentTurn(nextClientTurn.getTurnNumber());
            ApiUserResponseDto apiUserResponseDto = adminClientImpl.getUserById(nextClientTurn.getClientId());
            if (apiUserResponseDto.getPhoneNumber() != null) {
                log.info("Enviando sms a {}", apiUserResponseDto.getPhoneNumber());
                smService.sendSms(apiUserResponseDto.getPhoneNumber(), TURN_MESSAGE_2 + turn.getName());
            }
        }

        turn.setClientTurnList(clientTurnList);
        turnMongoRepository.save(turn);
        ApiTurnResponseDto apiTurnResponseDto = turnMapper.toDto(turn);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(apiTurnResponseDto);
            mqttService.publish(apiTurnResponseDto.getTopic(), json, 1, 1000L);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }

        return apiTurnResponseDto;
    }

    public ClientTurn findNextClientTurn(List<ClientTurn> turns) {
        return turns.stream()
                .filter(t -> t.getStatus().equals(Constants.STATUS.PENDIENTE.toString()))
                .min(Comparator.comparing(ClientTurn::getTurnNumber))
                .orElse(null);
    }

    private ClientTurn findCurrentClientTurn(List<ClientTurn> clientTurnList, String currentTurn) {
        return clientTurnList.stream()
                .filter(clientTurn -> clientTurn.getTurnNumber().equals(currentTurn))
                .findFirst()
                .orElse(null);
    }

    private void updateTurnStatusAndSaveInList(List<ClientTurn> clientTurnList, ClientTurn updatedClientTurn, String status) {
        updatedClientTurn.setStatus(status);
        updatedClientTurn.setModificatedDate(LocalDateTime.now());
        int index = clientTurnList.indexOf(updatedClientTurn);
        clientTurnList.set(index, updatedClientTurn);
    }

    @Override
    public ApiClientTurnResponseDto addClientToTurn(String turnId) {
        String userPhone = authUserService.getCurrentUser();
        Turn turn = turnMongoRepository.findBy_Id(turnId);
        if (turn == null) {
            throw new IllegalArgumentException("No existe turno activo");
        }
        ApiUserResponseDto apiUserResponseDto = adminClientImpl.getUserByPhone(userPhone);
        if (apiUserResponseDto == null) {
            throw new IllegalArgumentException("No existe usuario");
        }

        String userTurnNumber = Integer.toString(turn.getNextTurnNumber());
        boolean isTurnNumberExists = turn.getClientTurnList().stream()
                .peek(clientTurn -> System.out.println(clientTurn.getClientId()))
                .anyMatch(clientTurn -> clientTurn.getClientId().equals(apiUserResponseDto.getId()) && clientTurn.getStatus().equals(Constants.STATUS.PENDIENTE.name()));

        if (isTurnNumberExists) {
            System.out.println("El número de turno ya ha sido asignado");
            throw new IllegalArgumentException("El número de turno ya ha sido asignado");
        }

        ClientTurn clientTurn = ClientTurn.builder()
                .clientId(apiUserResponseDto.getId())
                .status(Constants.STATUS.PENDIENTE.name())
                .turnNumber(userTurnNumber)
                .createdDate(LocalDateTime.now())
                .build();
        ApiClientTurnResponseDto apiClientTurnResponseDto = turnMapper.toDtoClient(clientTurn);

        turn.getClientTurnList().add(clientTurn);
        turnMongoRepository.save(turn);

        smService.sendSms(userPhone, TURN_MESSAGE + userTurnNumber + " en " + turn.getName());

        return apiClientTurnResponseDto;
    }

    @Override
    public List<ApiTurnResponseDto> getAllTurns() {
        try {
            List<Turn> listTurn = turnMongoRepository.findByCreatedDate();
            if (listTurn.isEmpty()) {
                throw new ApiResourceNotFoundException("No hay turnos activos para el usuario actual");
            }
            return turnMapper.fromEntitiesToDtos(listTurn);
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public ApiTurnResponseDto getTurn(String identifier) {
        try {
            Turn turn = turnMongoRepository.findBy_Id(identifier);
            if (turn == null) {
                throw new ApiResourceNotFoundException("No existe turno activo");
            }
            ApiTurnResponseDto apiTurnResponseDto = turnMapper.toDto(turn);
            return apiTurnResponseDto;
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }


}
