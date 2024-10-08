package org.example.messgesender.repository;

import org.example.messgesender.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {
}
