package com.example.piper.piperchat.controller;

import com.example.piper.piperchat.DTO.RoomDTO;
import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.incoming_data.NewGroup;
import com.example.piper.piperchat.incoming_data.NewRoom;
import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.outgoing_data.PrivateRoomDetails;
import com.example.piper.piperchat.service.RoomService;
import com.example.piper.piperchat.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;

    @PostMapping("/new-room")
    public ResponseEntity<?> createARoom(@RequestBody NewRoom newRoom){
//         TODO
//        Check if both users exist
        UserDTO otherUser = userService.findUser(newRoom.getParticipants().get(1));
        if (otherUser == null) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "User does not exist");
            return ResponseEntity.badRequest().body(error);
        }
        boolean isRoomDuplicate = roomService.checkRoomDuplicity(newRoom.getName()) ||
                roomService.checkRoomDuplicity(newRoom.getAlternateRoomName());
        if(isRoomDuplicate){
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "Room already exists");
            return ResponseEntity.badRequest().body(error);
        }
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(newRoom, roomDTO);
        PrivateRoomDetails privateRoomDetails = new PrivateRoomDetails();
        RoomDTO createdRoom = roomService.createARoom(roomDTO);
        BeanUtils.copyProperties(createdRoom, privateRoomDetails);
        return ResponseEntity.ok().body(createdRoom);
    }

    @PostMapping("/new-group")
    public ResponseEntity<?> createAGroup(@RequestBody NewGroup newGroup){
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(newGroup, roomDTO);
        RoomDTO createdGroup = roomService.createAGroup(roomDTO);
        return ResponseEntity.ok().body("Room created successfully");

    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getRoomMessages(@PathVariable Long roomId){
        List<Message> messages = roomService.getMessages(roomId);
        return ResponseEntity.ok().body(messages);
    }
}
