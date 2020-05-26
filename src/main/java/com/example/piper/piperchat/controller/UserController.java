package com.example.piper.piperchat.controller;

import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.model.Profile;
import com.example.piper.piperchat.model.Room;
import com.example.piper.piperchat.model.User;
import com.example.piper.piperchat.outgoing_data.UserProfile;
import com.example.piper.piperchat.security.CustomUserDetail;
import com.example.piper.piperchat.service.AmazonS3Service;
import com.example.piper.piperchat.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AmazonS3Service amazonS3Service;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username, @AuthenticationPrincipal CustomUserDetail loggedInUser){
        UserDTO user = userService.findUser(username);
        if(user == null){
            return ResponseEntity.badRequest().body("User does not exist. Please use a real username");
        }
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(user, userProfile);
        List<Room> rooms = user.getRooms();
        return ResponseEntity.ok().body(rooms);
    }

    @PostMapping("/{username}/display-image")
    public ResponseEntity<?> updateUserDisplayImage(@PathVariable String username, @RequestParam("displayImage") MultipartFile image){
        String fileUrl = amazonS3Service.uploadImage(image);
        if (fileUrl != null){
            Profile profile = userService.updateUserDisplayImage(username, fileUrl);
            return ResponseEntity.ok().body(profile);
        }
        return ResponseEntity.unprocessableEntity().body("Something went wrong. Please try again later");
    }
}
