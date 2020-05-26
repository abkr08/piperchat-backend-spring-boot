package com.example.piper.piperchat.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Account {

//    private String email;
    private String password;

}
