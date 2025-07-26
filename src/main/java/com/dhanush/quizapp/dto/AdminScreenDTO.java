package com.dhanush.quizapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminScreenDTO {

    private Long id;

    private LocalDateTime deadLine;

    private int duration;

    private int numberOfQuestions;

    private String title;

    private String description;
}
