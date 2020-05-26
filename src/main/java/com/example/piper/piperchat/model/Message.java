package com.example.piper.piperchat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    private String sender;
//    @ManyToOne(fetch = FetchType.LAZY)

//    private long roomId;
    private Date createdAt;

    public long getId() {
        return id;
    }

    public void setId(long message_id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

//    public void addRoom(Room room){
//        setRoom(room);
//        room.getMessages().add(this);
//    }

}
