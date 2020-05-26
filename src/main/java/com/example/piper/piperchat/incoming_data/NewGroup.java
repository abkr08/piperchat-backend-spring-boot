package com.example.piper.piperchat.incoming_data;

import com.example.piper.piperchat.model.Message;
import com.example.piper.piperchat.model.RoomType;
import com.example.piper.piperchat.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewGroup {
    private String name;
    private String createdBy;
    private RoomType roomType;
    private List<String> participants;
    private String admin;
}
