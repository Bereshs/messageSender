package org.example.messgesender.post.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageStatus;
import org.example.messgesender.post.PostService;
import org.example.messgesender.service.MessageEventService;
import org.springframework.scheduling.annotation.Async;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EmailPostServiceEmulator implements PostService {
    private final Random random;
    private final MessageEventService service;

    public EmailPostServiceEmulator(Random random, MessageEventService service) {
        log.info("starting emailPostServiceEmulator");
        this.random = random;
        this.service=service;
    }


    @Async
    @Override
    public void send(MessageEntity message) throws MessageSenderException {
        service.saveStatus(message, MessageStatus.SENDING);
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(message.getDestination());
        if (!matcher.find()) throw new MessageSenderException("Email is not correct");
        sleep();
    }

    @Override
    public void addToQueue(MessageEntity message) throws MessageSenderException {
        service.saveStatus(message, MessageStatus.WAIT);
        sleep();
    }

    @Override
    public void sent(MessageEntity message) throws MessageSenderException {
        service.saveStatus(message, MessageStatus.SENT);
        if (errorGenerator()) throw new MessageSenderException("Error connection to server");
        sleep();
    }

    private boolean errorGenerator() {
        return false;//random.nextBoolean();
    }

    private int randomizer() {
        return random.nextInt(0, 10000);
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(randomizer());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
