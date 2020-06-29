package com.example.piper.piperchat.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("Private")
public class PrivateRoom extends Room {
    private Boolean isARequest;
}
