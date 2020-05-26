package com.example.piper.piperchat.repository;

import com.example.piper.piperchat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    Message findById(Long id);
    Message findBySender(String sender);
}
