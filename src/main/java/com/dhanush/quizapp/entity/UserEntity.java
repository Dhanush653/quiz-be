package com.dhanush.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isAdmin;

    @OneToMany(mappedBy = "createdBy")
    private List<QuizRoomEntity> createdRooms;

    @OneToMany(mappedBy = "user")
    private List<QuizSessionEntity> quizSessions;
}