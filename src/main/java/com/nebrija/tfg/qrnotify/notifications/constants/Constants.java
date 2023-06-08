package com.nebrija.tfg.qrnotify.notifications.constants;

public class Constants {

    private Constants(){}

    public static final String MODULE_NAME = "qrnotify-notifications";

    public static final String TURN_MESSAGE = "Su turno es el número: ";

    public static final String TURN_MESSAGE_2 = "Pronto sera su turno. Por favor, acuda al puesto: ";


    public enum STATUS {
        PENDIENTE,
        EN_CURSO,
        COMPLETADO
    }

}

