package com.example.piper.piperchat.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Profile {
    private String displayImage;// = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fbleacherreport.com%2Farticles%2F2835956-mesut-ozil-says-i-dont-think-theres-a-chance-hell-leave-arsenal-this-summer&psig=AOvVaw0C-jnplvMSmqNU0Lz8SMVg&ust=1587762216439000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNjR-6-5_-gCFQAAAAAdAAAAABAE";
    private String bio;// = "a great football player";
}
