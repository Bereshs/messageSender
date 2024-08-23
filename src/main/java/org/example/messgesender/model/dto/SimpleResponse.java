package org.example.messgesender.model.dto;

import lombok.Getter;

@Getter
public class SimpleResponse {
    private final String message;

    public SimpleResponse(String message) {
        this.message = message;
    }
}
