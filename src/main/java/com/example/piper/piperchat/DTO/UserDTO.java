package com.example.piper.piperchat.DTO;

import com.example.piper.piperchat.model.Profile;
import com.example.piper.piperchat.model.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private long userId;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String fullName;
    private Date createdAt;
    private Date UpdatedAt;
    private List<Room> rooms = new ArrayList<>();
    private Profile profile = new Profile();
}
