package com.dhanush.quizapp.controller;

import com.dhanush.quizapp.dto.*;
import com.dhanush.quizapp.entity.QuizRoomEntity;
import com.dhanush.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-rooms/admin")
    private ResponseEntity<AdminRoomsResponseDTO> getAdminRooms(@RequestHeader("Authorization") String token){
        AdminRoomsResponseDTO response = userService.getAdminRooms(token);
        return ResponseEntity.ok(response);
    }
}
