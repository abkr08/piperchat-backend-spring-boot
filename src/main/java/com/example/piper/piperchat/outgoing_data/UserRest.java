package com.example.piper.piperchat.outgoing_data;

import lombok.Data;

import java.util.Date;

@Data
public class UserRest {
    private long userId;
    private String username;
    private String email;
    private String fullName;
    private Date createdAt;
//    private Date UpdatedAt;
}
