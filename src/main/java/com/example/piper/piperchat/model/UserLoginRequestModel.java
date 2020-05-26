package com.example.piper.piperchat.model;

import lombok.Data;

@Data
public class UserLoginRequestModel {
    private String email;
    private String password;
}
