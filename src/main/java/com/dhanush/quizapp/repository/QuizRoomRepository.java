package com.dhanush.quizapp.repository;

import com.dhanush.quizapp.entity.QuizRoomEntity;
import com.dhanush.quizapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRoomRepository extends JpaRepository<QuizRoomEntity, Long> {
    List<QuizRoomEntity> findByCreatedBy(UserEntity user);
}
