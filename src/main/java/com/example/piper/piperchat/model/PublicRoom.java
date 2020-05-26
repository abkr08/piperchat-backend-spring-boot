package com.example.piper.piperchat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Public")
@Data
public class PublicRoom extends Room {

    private String displayImage;
    private String admin;
    private RoomType roomType = RoomType.PUBLIC;
}
