package com.dhanush.quizapp.controller;

import com.dhanush.quizapp.dto.AuthResponseDTO;
import com.dhanush.quizapp.dto.LoginDTO;
import com.dhanush.quizapp.dto.RegistrationDTO;
import com.dhanush.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO){
        String message = userService.registerNewUser(registrationDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    private ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginDTO loginDTO){
        AuthResponseDTO response = userService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }
}
