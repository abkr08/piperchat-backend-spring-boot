package com.example.piper.piperchat.service;

import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.incoming_data.UserRegistrationDetails;
import com.example.piper.piperchat.model.User;
import com.example.piper.piperchat.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class UserValidator {
    private HashMap<String, String> errors = new HashMap<>();
    @Autowired
    UserRepository userRepository;
    public Boolean validate(UserDTO user){

        if(!user.getPassword().equals(user.getConfirmPassword())){
            this.errors.put("confirmPassword", "Passwords do not match");
            return false;
        }
        if (user.getPassword().matches("[a-zA-Z]+")){
            this.errors.put("password", "Password must contain at least one number");
            return false;
        }
        if (user.getPassword().matches("[0-9]+")) {
            this.errors.put("password", "Password must contain at least one letter");
            return false;
        }
        if (emailExist(user.getEmail())) {
            this.errors.put("email", "There is an account with that email address");
            return false;
        }
        if (usernameExists(user.getUsername())){
            this.errors.put("username", "This username is already registered. Try using a different username");
            return false;
        }
        return true;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
