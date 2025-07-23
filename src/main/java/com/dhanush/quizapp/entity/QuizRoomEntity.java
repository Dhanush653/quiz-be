package com.dhanush.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz_rooms")
@Data
public class QuizRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int numberOfQuestions;

    private int durationMinutes;

    private LocalDateTime deadlineTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

    @OneToMany(mappedBy = "quizRoom", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "quizRoom", cascade = CascadeType.ALL)
    private List<QuizAccessEntity> allowedUsers;

    @OneToMany(mappedBy = "quizRoom")
    private List<QuizSessionEntity> quizSessions;
}