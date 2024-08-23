package org.example.messgesender.mapper;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.MessageEventEntity;
import org.example.messgesender.model.MessageStatus;
import org.example.messgesender.model.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageMapperTest {

    MessageMapper mapper = Mappers.getMapper(MessageMapper.class);


    @Test
    void toMessageEntityNull() {
        MessageDto messageDto =  new MessageDto(null, null, null, null);
        MessageEntity message = mapper.toMessageEntity(messageDto);
        assertEquals(messageDto.destination(), message.getDestination());
        assertEquals(messageDto.message(), message.getMessage());
    }

    @Test
    void toMessageEntity() {
        MessageDto messageDto = getMessageDto();
        MessageEntity message = mapper.toMessageEntity(messageDto);
        assertEquals(messageDto.destination(), message.getDestination());
        assertEquals(messageDto.message(), message.getMessage());
    }

    @Test
    void toMessageDto() {

        MessageEntity message = getMessageEntity();
        MessageDto messageDto = mapper.toMessageDto(message);
        assertEquals(messageDto.destination(), message.getDestination());
        assertEquals(messageDto.message(), message.getMessage());;
        assertEquals(messageDto.events().get(0).status(), MessageStatus.SENDING);

    }

    MessageEntity getMessageEntity() {
        MessageEntity message = new MessageEntity();
        message.setId(1L);
        message.setDestination("mail@mail.ru");
        message.setMessage("test message");
        List<MessageEventEntity> events = new ArrayList<>();
        events.add(new MessageEventEntity(MessageStatus.SENDING, message));
        events.add(new MessageEventEntity(MessageStatus.ERROR, message));
        message.setEvents(events);
        return message;
    }

    MessageDto getMessageDto() {
        return new MessageDto(null, "mail@mail.ru", "test message", null);
    }
}