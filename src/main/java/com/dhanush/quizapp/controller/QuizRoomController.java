package com.dhanush.quizapp.controller;

import com.dhanush.quizapp.dto.QuizRoomCreateDTO;
import com.dhanush.quizapp.service.QuizRoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz-room")
public class QuizRoomController {

    @Autowired
    private QuizRoomService quizRoomService;

    @PostMapping("/create")
    private ResponseEntity<String> createQuizRoom(@RequestBody QuizRoomCreateDTO quizRoomCreateDTO, @RequestHeader("Authorization") String token){
        String response = quizRoomService.createQuizRoom(quizRoomCreateDTO, token);
        return ResponseEntity.ok(response);
    }
}
