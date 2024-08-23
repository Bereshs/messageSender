package org.example.messgesender.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MessageDto(
        Long id,
        String destination,
        String message,
        List<MessageEventDto> events

) {
}
