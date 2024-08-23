package org.example.messgesender.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.messgesender.exception.MessageSenderException;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "message-events")
@NoArgsConstructor
public class MessageEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private MessageEntity message;
    @Enumerated(EnumType.ORDINAL)
    private MessageStatus status;
    @CreationTimestamp
    private Instant timeStamp;

    public MessageEventEntity(MessageStatus status, MessageEntity message) {
        this.status=status;
        this.message=message;
    }

}
