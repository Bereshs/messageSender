package org.example.messgesender.repository;

import org.example.messgesender.model.MessageEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageEventRepository extends JpaRepository<MessageEventEntity, Long> {

}
