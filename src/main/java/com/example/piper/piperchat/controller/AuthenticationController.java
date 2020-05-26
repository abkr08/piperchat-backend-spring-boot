package com.example.piper.piperchat.controller;

import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.incoming_data.UserRegistrationDetails;
import com.example.piper.piperchat.model.User;
import com.example.piper.piperchat.outgoing_data.UserRest;
import com.example.piper.piperchat.service.UserService;
import com.example.piper.piperchat.service.UserValidator;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDetails userRegistrationDetails){
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userRegistrationDetails, userDto);
        if(!userValidator.validate(userDto)){
            HashMap<String, String> errors = userValidator.getErrors();
            return ResponseEntity.badRequest().body(errors);
        }
        UserRest returnValue = new UserRest();
        UserDTO createdUser = userService.registerNewUserAccount(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return ResponseEntity.ok().body(returnValue);
    }
}
