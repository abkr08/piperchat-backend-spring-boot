package com.example.piper.piperchat.outgoing_data;

import com.example.piper.piperchat.model.Profile;
import com.example.piper.piperchat.model.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserLoginResponse {
    private long userId;
    private String username;
    private String email;
    private String fullName;
    private List<Room> rooms = new ArrayList<>();
    private Profile profile = new Profile();
}
