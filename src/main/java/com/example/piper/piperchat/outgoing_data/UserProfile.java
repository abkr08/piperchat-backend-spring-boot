package com.example.piper.piperchat.outgoing_data;

import com.example.piper.piperchat.model.Profile;
import lombok.Data;

@Data
public class UserProfile extends UserRest {
    private Profile profile = new Profile();
}
