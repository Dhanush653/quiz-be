package com.dhanush.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "quiz_access",
        uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_room_id", "email"})
)
@Data
public class QuizAccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "quiz_room_id")
    private QuizRoomEntity quizRoom;
}