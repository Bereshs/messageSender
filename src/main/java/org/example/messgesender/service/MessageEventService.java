package org.example.messgesender.service;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageEventEntity;
import org.example.messgesender.model.MessageStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageEventService {

    void save(MessageEventEntity event);

    List<MessageEventEntity> findByMessageId(long id);

    void saveStatus(MessageEntity message, MessageStatus status);
}
