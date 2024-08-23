package org.example.messgesender.service.impl;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.repository.MessageEntityRepository;
import org.example.messgesender.service.MessageEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MessageEntityServiceImpl implements MessageEntityService {
    private final MessageEntityRepository repository;

    @Autowired
    public MessageEntityServiceImpl(MessageEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(MessageEntity message) {
        repository.save(message);
        repository.flush();
    }

    @Override
    public Optional<MessageEntity> findById(Long id) {
        return repository.findById(id);
    }
}
