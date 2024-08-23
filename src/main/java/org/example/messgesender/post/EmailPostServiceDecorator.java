package org.example.messgesender.post;

import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.model.MessageEntity;

public abstract class EmailPostServiceDecorator implements PostService {
    protected PostService postService;

    public EmailPostServiceDecorator(PostService postService) {
        this.postService = postService;
    }

    public void send(MessageEntity message) throws MessageSenderException {
        postService.send(message);
    }

    public void addToQueue(MessageEntity message) throws MessageSenderException {
        postService.addToQueue(message);
    }

    public void sent(MessageEntity message) throws MessageSenderException {
        postService.sent(message);
    }
}
