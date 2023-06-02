package com.nebrija.tfg.qrnotify.notifications.services;

import com.nebrija.tfg.qrnotify.notifications.model.api.ApiClientTurnRequestDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiClientTurnResponseDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiTurnRequestDto;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiTurnResponseDto;

import java.util.List;

public interface NotificationPublisher {

    ApiTurnResponseDto activateTurn(); //busca el turno en el servicio del topico
    // por el nombre del admin y lo activa(creando un nuevo turno en el servicio de notificaciones)
    // una vez activado deberia guardarse en la base de datos para luego recuperrarlo

    ApiTurnResponseDto passTurn(); //Debe actualizar el turno y mandarseloa
    // a los clientes que esten suscritos para que sepan que les toca y seguir el estado

    ApiClientTurnResponseDto addClientToTurn(String turnId);
    //añade un cliente a un turno y aqui una duda si notificar a todos los clientes que esten suscritos
    List<ApiTurnResponseDto> getAllTurns(); //devuelve todos los turnos  para la pantalla de usarios
    // del servicio de notificaciones para que puedan ver los turnos que hay y apuntarse a uno

    ApiTurnResponseDto getTurn(String identifier); //devuelve un turno por su identificador
}
