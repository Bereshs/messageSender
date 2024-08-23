package org.example.messgesender.service.impl;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageEventEntity;
import org.example.messgesender.model.MessageStatus;
import org.example.messgesender.repository.MessageEventRepository;
import org.example.messgesender.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageEventServiceImpl implements MessageEventService {
    private final MessageEventRepository repository;

    @Autowired
    public MessageEventServiceImpl(MessageEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(MessageEventEntity event) {
        repository.save(event);
    }

    @Override
    public List<MessageEventEntity> findByMessageId(long id) {
        return null;
    }

    @Override
    public void saveStatus(MessageEntity message, MessageStatus status) {
        MessageEventEntity messageEvent = new MessageEventEntity(status, message);
        save(messageEvent);
    }
}
