package com.dhanush.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "answers")
@Data
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_session_id")
    private QuizSessionEntity quizSession;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    private String selectedOption;

    @Column(name = "is_correct")
    private boolean correct;
}