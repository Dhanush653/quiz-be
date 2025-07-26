package com.dhanush.quizapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private String question;

    private List<String> options;

    private int correctOptionIndex;
}
