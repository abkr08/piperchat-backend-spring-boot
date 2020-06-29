package com.example.piper.piperchat.controller;

import com.example.piper.piperchat.DTO.RoomDTO;
import com.example.piper.piperchat.incoming_data.NewMessage;
import com.example.piper.piperchat.incoming_data.Notification;
import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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

    @MessageMapping("/{username}")
    public void handleNotifications(@DestinationVariable String username, Notification notification){
        if(notification.getType().equals("acceptChatRequest")){
            RoomDTO room = roomService.acceptChatRequest(notification.getData());
            notification.setData(room);
            simpMessagingTemplate.convertAndSend("/socket-publisher/" + notification.getTo(), notification);
        } else if(notification.getType().equals("denyChatRequest")){
            roomService.denyChatRequest(notification.getData());
            simpMessagingTemplate.convertAndSend("/socket-publisher/" + notification.getTo(), notification);
        } else {
            simpMessagingTemplate.convertAndSend("/socket-publisher/" + notification.getTo(), notification);
        }
    }
}
