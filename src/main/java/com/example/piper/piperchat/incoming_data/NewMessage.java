package com.example.piper.piperchat.incoming_data;

import lombok.Data;

import java.util.Date;

@Data
public class NewMessage {
    private String text;
    private String sender;
    private long roomId;
}
