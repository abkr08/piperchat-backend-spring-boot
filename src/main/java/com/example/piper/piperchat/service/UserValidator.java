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
    @Autowired
    UserRepository userRepository;
    private HashMap<String, String> errors;
    public Boolean validate(UserDTO user){
        errors = new HashMap<>();
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.put("error", "Passwords do not match");
            return false;
        }
        if (user.getPassword().matches("[a-zA-Z]+")){
            errors.put("error", "Password must contain at least one number");
            return false;
        }
        if (user.getPassword().matches("[0-9]+")) {
            errors.put("error", "Password must contain at least one letter");
            return false;
        }
        if (emailExist(user.getEmail())) {
            errors.put("error", "There is an account with that email address");
            return false;
        }
        if (usernameExists(user.getUsername())){
            errors.put("error", "This username is already registered. Try using a different username");
            return false;
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.put("error", "Email cannot be empty");
            return false;
        }
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            errors.put("error", "Full name cannot be empty");
            return false;
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            errors.put("error", "Username cannot be empty");
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
