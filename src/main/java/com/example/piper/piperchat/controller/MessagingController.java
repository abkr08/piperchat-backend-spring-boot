package com.example.piper.piperchat.controller;

import com.example.piper.piperchat.incoming_data.NewMessage;
import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class MessagingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private RoomService roomService;

    @MessageMapping("/send/message")
    public void sendMessage(NewMessage newMessage){
        // Todo
        // Find a way to make sure I sync the adding of message to socket and to db
        Message message = roomService.addMessage(newMessage);
        simpMessagingTemplate.convertAndSend("/socket-publisher/" + newMessage.getRoomId(), newMessage);
    }

    @MessageMapping("/call")
    public void call(HashMap<Object, Object> message){
        simpMessagingTemplate.convertAndSend("/socket-publisher/call/" + message.get("to"), message);
    }
}
