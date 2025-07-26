package com.dhanush.quizapp.repository;

import com.dhanush.quizapp.entity.QuizAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAccessRepository extends JpaRepository<QuizAccessEntity, Long> {
}
