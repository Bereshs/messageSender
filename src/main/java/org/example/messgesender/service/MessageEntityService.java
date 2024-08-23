package org.example.messgesender.service;

import org.example.messgesender.model.MessageEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface MessageEntityService {

    void save(MessageEntity message);

    Optional<MessageEntity> findById(Long id);

}
