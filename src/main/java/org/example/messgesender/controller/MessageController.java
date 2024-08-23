package org.example.messgesender.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.mapper.MessageMapper;
import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.dto.MessageDto;
import org.example.messgesender.post.PostService;
import org.example.messgesender.service.MessageEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@Tag(name = "Message",
        description = "message controller")
@RestController
public class MessageController {
    private final MessageEntityService service;
    private final MessageMapper mapper;
    private final PostService postService;

    @Autowired
    public MessageController(MessageEntityService service, MessageMapper mapper, PostService postService) {
        this.service = service;
        this.mapper = mapper;
        this.postService = postService;
    }

    @Operation(summary = "send message")
    @PostMapping("/message")
    public ResponseEntity<MessageDto> postMessageHandler(@RequestBody MessageDto messageDto) throws MessageSenderException {
        MessageEntity message = mapper.toMessageEntity(messageDto);

        if (message.getMessage() == null || message.getDestination() == null)
            throw new MessageSenderException("Wrong message");
        service.save(message);


        postService.send(message);
        return ResponseEntity.ok(
                mapper.toMessageDto(message)
        );
    }


    @Operation(summary = "get messgae")
    @GetMapping("/message/{id}")
    public ResponseEntity<MessageDto> getMessageHandler(@PathVariable("id") Long id) throws MessageSenderException {
        MessageEntity message = service.findById(id).orElseThrow(
                () -> new MessageSenderException("Wrong message id"));


        return ResponseEntity.ok(mapper.toMessageDto(message));
    }
}
