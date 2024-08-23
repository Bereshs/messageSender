package org.example.messgesender.service;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageEventEntity;
import org.example.messgesender.model.MessageStatus;
import org.example.messgesender.repository.MessageEntityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MessageEntityServiceTest {


    @MockBean
    MessageEntityService service;

    @MockBean
    MessageEntityRepository repository;

    @Test
    void save() {

        service.save(getMessageEntity());
        Mockito.verify(service, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findById() {
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(Optional.of(getMessageEntity()));

        Optional<MessageEntity> entity = service.findById(1L);
        assertTrue(entity.isPresent());
        assertEquals(entity.get().getMessage(), getMessageEntity().getMessage());

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