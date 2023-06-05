package com.nebrija.tfg.qrnotify.notifications.controllers;

import com.nebrija.tfg.qrnotify.notifications.clients.impl.AdminClientImpl;
import com.nebrija.tfg.qrnotify.notifications.model.api.*;
import com.nebrija.tfg.qrnotify.notifications.models.ApiUserResponseDto;
import com.nebrija.tfg.qrnotify.notifications.services.NotificationPublisher;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "${chen.base_path}")
public class Controller implements TestApi, AdminApi,UserApi, TurnApi {

    @Autowired
    private NotificationPublisher notificationPublisher;
    @Autowired
    private AdminClientImpl adminClient;
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TestApi.super.getRequest();
    }

    @Override
    @GetMapping("/test")
    public ResponseEntity<ApiGeneralResponse> test() {
        List< ApiUserResponseDto> users = adminClient.getAllUsers();
        System.out.println(users);
        ApiGeneralResponse generalResponse = new ApiGeneralResponse();
        generalResponse.setMessage("Test OK");
        return ResponseEntity.ok(generalResponse);
    }

    @Override
    public ResponseEntity<ApiTurnResponseDto> createTurn() {
        ApiTurnResponseDto apiTurnResponseDto = notificationPublisher.activateTurn();
        return new ResponseEntity<>(apiTurnResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiTurnResponseDto> passTurn() {
        ApiTurnResponseDto apiTurnResponseDto = notificationPublisher.passTurn();
        return new ResponseEntity<>(apiTurnResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiClientTurnResponseDto> requestTurn(@NotNull @ApiParam(value = "turnId", required = true) @Valid @RequestParam(value = "turnId", required = true) String turnId) {
        ApiClientTurnResponseDto  addClientToTurn = notificationPublisher.addClientToTurn(turnId);
        return new ResponseEntity<>(addClientToTurn, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ApiTurnResponseDto>> getTurns() {
        List<ApiTurnResponseDto> turns = notificationPublisher.getAllTurns();
        return new ResponseEntity<>(turns, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiTurnResponseDto> getTurn(@ApiParam(value = "turnId",required=true) @PathVariable("turnId") String turnId) {
        ApiTurnResponseDto turn = notificationPublisher.getTurn(turnId);
        return new ResponseEntity<>(turn, HttpStatus.OK);
    }
    }
