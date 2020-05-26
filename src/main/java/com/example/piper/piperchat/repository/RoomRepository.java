package com.example.piper.piperchat.repository;

import com.example.piper.piperchat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomId(Long id);
    Room findByName(String name);
}
