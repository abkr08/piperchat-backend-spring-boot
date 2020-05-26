package com.example.piper.piperchat.incoming_data;

import lombok.Data;

@Data
public class UserRegistrationDetails {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String fullName;
}
