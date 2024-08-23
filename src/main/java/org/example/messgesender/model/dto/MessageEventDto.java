package org.example.messgesender.model.dto;
import org.example.messgesender.model.MessageStatus;
import java.time.Instant;

public record MessageEventDto(
        String timeStamp,
        MessageStatus status
) {
}
