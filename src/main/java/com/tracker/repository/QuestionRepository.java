package com.tracker.repository;

import com.tracker.entity.Difficulty;
import com.tracker.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUserId(Long userId);
    List<Question> findByUserIdAndDifficulty(Long userId, Difficulty difficulty); // ✅ new
}