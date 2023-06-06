package com.nebrija.tfg.qrnotify.notifications.exceptions;

import com.nebrija.tfg.qrnotify.notifications.model.api.ApiError;
import com.nebrija.tfg.qrnotify.notifications.model.api.ApiErrorResponse;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import feign.FeignException;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

import static com.nebrija.tfg.qrnotify.notifications.constants.Constants.MODULE_NAME;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiRequestException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("400");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiResourceNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("404");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= ServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ServerErrorException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("500");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value= NotImplementedException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(NotImplementedException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("501");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(FeignException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode(String.valueOf(ex.status()));
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.valueOf(ex.status()));
    }

    @ExceptionHandler(value = MqttException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(MqttException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("500");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = TwilioException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(TwilioException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ApiError apiError = new ApiError();
        apiError.setCode("500");
        apiError.setMessage(ex.getMessage());
        apiError.setDetails(MODULE_NAME);
        apiError.setRequestId(UUID.randomUUID().toString());
        apiErrorResponse.setError(apiError);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
