package org.example.messgesender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.messgesender.exception.MessageSenderException;
import org.example.messgesender.mapper.MessageMapper;
import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageEventEntity;
import org.example.messgesender.model.MessageStatus;
import org.example.messgesender.model.dto.MessageDto;
import org.example.messgesender.post.PostService;
import org.example.messgesender.service.MessageEntityService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockBean
    MessageEntityService service;

    @MockBean
    MessageMapper mapper;

    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;



    @Test
    void postMessageHandler() throws Exception {

        Mockito.when(mapper.toMessageDto(Mockito.any())).thenReturn(getMessageDto());

        Mockito.when(mapper.toMessageEntity(Mockito.any())).thenReturn(getMessageEntity());

        mockMvc.perform(
                        post("/message").
                                contentType(MediaType.APPLICATION_JSON).
                                content(getJson(getMessageDto())))

                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("mail@mail.ru")));
    }

    @Test
    void getMessageHandler() throws Exception {
        Mockito.when(service.findById(Mockito.any(Long.class))).thenReturn(
                Optional.of(getMessageEntity()));
        Mockito.when(mapper.toMessageDto(Mockito.any())).thenReturn(
                getMessageDto());

        mockMvc.perform(get("/message/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("mail@mail.ru")));

    }


    @Test
    void getMessageThrowsHandler() throws Exception {
        Mockito.when(service.findById(Mockito.any(Long.class))).thenAnswer(i -> {
            throw new MessageSenderException("message");
        });

        mockMvc.perform(get("/message/1"))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().string(Matchers.containsString("message")));

    }

    byte[] getJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o).getBytes();
    }

    MessageDto getMessageDto() {
        return new MessageDto(0L, "mail@mail.ru", "test message", null);
    }


    MessageEntity getMessageEntity() {
        MessageEntity message = new MessageEntity();
        message.setDestination("mail@mail.ru");
        message.setMessage("test message");
        message.setId(1L);
        List<MessageEventEntity> events = new ArrayList<>();
        events.add(new MessageEventEntity(MessageStatus.SENDING, message));
        events.add(new MessageEventEntity(MessageStatus.ERROR, message));
        message.setEvents(events);
        return message;
    }

}