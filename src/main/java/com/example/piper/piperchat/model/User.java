package com.example.piper.piperchat.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long userId;

    @NotNull(message = "Full name cannot be empty")
    private String fullName;

    @NotNull(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @Email
    private String email;

    @ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
//            (fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Room> rooms = new ArrayList<>();


    private Date createdAt;
    private Date UpdatedAt;

    @Embedded
    private Profile profile = new Profile();

    @PrePersist
    protected void onCreate(){
        createdAt = new Date();
        profile.setDisplayImage("https://source.unsplash.com/user/erondu/300x300");
        profile.setBio("a great football player");
    }

    @PreUpdate
    protected void onUpdate(){
        UpdatedAt = new Date();
    }

}
