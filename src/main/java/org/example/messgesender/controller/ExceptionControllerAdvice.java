package org.example.messgesender.controller;

import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.model.dto.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MessageSenderException.class)
    public ResponseEntity<SimpleResponse> messageSenderExceptionHandler(MessageSenderException e) {
        return ResponseEntity.status(400).body(
                new SimpleResponse(e.getMessage())
        );
    }
}
