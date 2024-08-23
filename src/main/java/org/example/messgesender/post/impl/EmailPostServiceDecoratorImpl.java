package org.example.messgesender.post.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.post.EmailPostServiceDecorator;
import org.example.messgesender.post.PostService;


@Slf4j
public class EmailPostServiceDecoratorImpl extends EmailPostServiceDecorator {

    public EmailPostServiceDecoratorImpl(PostService postService) {
        super(postService);
    }

    @Override
    public void send(MessageEntity message) throws MessageSenderException {
        log.info("Starting send method");
        postService.send(message);
        addToQueue(message);
    }

    @Override
    public void addToQueue(MessageEntity message) throws MessageSenderException {
        log.info("Starting addToQueue method");
        postService.addToQueue(message);
        sent(message);
    }

    @Override
    public void sent(MessageEntity message) throws MessageSenderException {
        log.info("Starting sent method");
        postService.sent(message);
    }
}
