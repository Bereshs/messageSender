package org.example.messgesender.post;

import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.model.MessageEntity;
import org.springframework.scheduling.annotation.Async;


public interface PostService {


    @Async
    void send(MessageEntity message) throws MessageSenderException;

    void addToQueue(MessageEntity message) throws MessageSenderException;

    void sent(MessageEntity message) throws MessageSenderException;

}
