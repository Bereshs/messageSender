package org.example.messgesender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String message;
    @OneToMany
    @JoinColumn(name = "message_id")
    private List<MessageEventEntity> events;
    @CreationTimestamp
    private Instant timeStamp;

}
