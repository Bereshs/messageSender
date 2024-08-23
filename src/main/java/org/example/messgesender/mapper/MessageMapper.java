package org.example.messgesender.mapper;

import org.example.messgesender.model.MessageEntity;
import org.example.messgesender.model.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "events", ignore = true)
    MessageEntity toMessageEntity(MessageDto messageDto);

    MessageDto toMessageDto(MessageEntity message);

}
