package com.example.piper.piperchat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance
//@DiscriminatorColumn(name = "Room Type", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class Room extends User {

    @Id
    @GeneratedValue
    private long roomId;

    private String name;

    private String createdBy;

    private RoomType roomType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "room_id")
//    @JoinColumn(name = "room_Id")
    private List<Message> messages = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> members = new ArrayList<>();

    private Date createdAt;
    private Date UpdatedAt;
    private Date lastMessageAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        UpdatedAt = new Date();
    }

    public void addMember(User member){
        members.add(member);
        member.getRooms().add(this);
    }

    public void removeUser(User user) {
        members.remove(user);
        user.getRooms().remove(this);
    }

    public void addMessage(Message message){
        getMessages().add(message);
        setLastMessageAt(new Date());
    }

}
