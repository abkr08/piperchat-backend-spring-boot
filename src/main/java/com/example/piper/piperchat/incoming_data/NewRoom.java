package com.example.piper.piperchat.incoming_data;

import com.example.piper.piperchat.model.RoomType;
import lombok.Data;

import java.util.List;

@Data
public class NewRoom {
    private RoomType roomType;
    private List<String> participants;
    private String name;
    private String createdBy;
    private String alternateRoomName;

}
