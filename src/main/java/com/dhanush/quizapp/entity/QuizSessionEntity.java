package com.dhanush.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz_sessions")
@Data
public class QuizSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_room_id")
    private QuizRoomEntity quizRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean submitted;

    private Integer score;

    @OneToMany(mappedBy = "quizSession", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers;
}