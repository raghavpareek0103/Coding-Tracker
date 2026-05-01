package com.tracker.service;

import com.tracker.entity.Question;
import java.util.List;
import java.util.Map;

public interface QuestionService {
    Question addQuestion(Question question);
    Map<String, Long> getStats(Long userId);
    List<Question> getAllByUser(Long userId);

    // New methods
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
    Question getById(Long id);
    List<Question> filterByDifficulty(Long userId, String difficulty);
}