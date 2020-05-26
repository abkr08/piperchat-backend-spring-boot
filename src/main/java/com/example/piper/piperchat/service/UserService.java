package com.example.piper.piperchat.service;

import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.model.Profile;
import com.example.piper.piperchat.model.User;

import com.example.piper.piperchat.repository.UserRepository;
import com.example.piper.piperchat.security.CustomUserDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO registerNewUserAccount(UserDTO user){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = new User();
        BeanUtils.copyProperties(user, createdUser);
        userRepository.save(createdUser);
        BeanUtils.copyProperties(createdUser, user);
        return user;
    }

    public UserDTO findUser(String username){
        User user = userRepository.findByUsername(username);

        if(user != null){
            UserDTO foundUser = new UserDTO();
            BeanUtils.copyProperties(user, foundUser);
            return foundUser;
        }
        return null;
    }

    public Profile updateUserDisplayImage(String username, String fileUrl){
        User user = userRepository.findByUsername(username);
        user.getProfile().setDisplayImage(fileUrl);
        userRepository.save(user);
        return user.getProfile();
    }

}