package com.example.piper.piperchat.DTO;

import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.model.RoomType;
import com.example.piper.piperchat.model.User;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class RoomDTO {

    private String name;
    private String createdBy;
    private RoomType roomType;
    private List<Message> messages = new ArrayList<>();
    private List<String> participants = new ArrayList<>();
    private List<User> members = new ArrayList<>();
    private String user1;
    private String user2;
    private String admin;
    private Date createdAt;
    private Date UpdatedAt;
    private Date lastMessageAt;
}
