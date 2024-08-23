package org.example.messgesender.configuration;

import org.example.messgesender.post.PostService;
import org.example.messgesender.post.impl.EmailPostServiceDecoratorImpl;
import org.example.messgesender.post.impl.EmailPostServiceEmulator;
import org.example.messgesender.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Random;

@Configuration
public class PostServiceConfiguration {

    private final MessageEventService service;

    @Autowired
    public PostServiceConfiguration(MessageEventService service) {
        this.service = service;
    }

    @Bean
    public PostService getEmailPostService() {
        Random random = new Random();
        PostService postServiceEmulator = new EmailPostServiceEmulator(random, service);
        return new EmailPostServiceDecoratorImpl(postServiceEmulator);
    }
}
