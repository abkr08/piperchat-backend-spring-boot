package com.example.piper.piperchat.model;

import javax.persistence.OneToMany;
import java.util.List;

public class Channel extends Room {
    @OneToMany
    private List<User> members;
}
