package com.riley.haircutAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(value = {ClientRequestException.class})
    public ResponseEntity<Object> handleClientRequestException(ClientRequestException e) {

        ClientException exception = new ClientException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("UTC"))
        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
