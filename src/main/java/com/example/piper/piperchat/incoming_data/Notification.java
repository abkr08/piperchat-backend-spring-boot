package com.example.piper.piperchat.incoming_data;

import com.example.piper.piperchat.DTO.RoomDTO;
import lombok.Data;

@Data
public class Notification {
    private String type;
    private String to;
    private String from;
    private RoomDTO data;
}
