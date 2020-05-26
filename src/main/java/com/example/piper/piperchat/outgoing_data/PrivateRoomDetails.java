package com.example.piper.piperchat.outgoing_data;

import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.model.RoomType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PrivateRoomDetails {
    private String name;
    private String user1;
    private String user2;
    private String createdBy;
    private RoomType roomType;
    private List<Message> messages;
    private Date createdAt;
    private Date UpdatedAt;
    private Date lastMessageAt;

}
