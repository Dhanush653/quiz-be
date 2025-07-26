package com.dhanush.quizapp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizRoomCreateDTO {

    private String title;

    private String description;

    private int duration;

    private LocalDateTime deadLine;

    private List<QuestionDTO> questions;

    private List<String> participantEmails;
}
