package com.nebrija.tfg.qrnotify.notifications.exceptions;

public class ApiResourceNotFoundException extends RuntimeException {

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

    public ApiResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
