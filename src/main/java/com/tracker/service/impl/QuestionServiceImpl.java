package com.tracker.service.impl;

import com.tracker.entity.Difficulty;
import com.tracker.entity.Question;
import com.tracker.repository.QuestionRepository;
import com.tracker.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired QuestionRepository questionRepo;

    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public Map<String, Long> getStats(Long userId) {
        List<Question> questions = questionRepo.findByUserId(userId);
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions found for this user");
        }
        return questions.stream()
                .collect(Collectors.groupingBy(
                        q -> q.getDifficulty().name(),
                        Collectors.counting()
                ));
    }

    @Override
    public List<Question> getAllByUser(Long userId) {
        return questionRepo.findByUserId(userId);
    }

    // ✅ Update question
    @Override
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existing = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        existing.setTitle(updatedQuestion.getTitle());
        existing.setDifficulty(updatedQuestion.getDifficulty());
        existing.setTopic(updatedQuestion.getTopic());
        existing.setSolvedDate(updatedQuestion.getSolvedDate());

        return questionRepo.save(existing);
    }

    // ✅ Delete question
    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepo.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepo.deleteById(id);
    }

    // ✅ Get by ID
    @Override
    public Question getById(Long id) {
        return questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    // ✅ Filter by difficulty
    @Override
    public List<Question> filterByDifficulty(Long userId, String difficulty) {
        try {
            Difficulty diff = Difficulty.valueOf(difficulty.toUpperCase());
            return questionRepo.findByUserIdAndDifficulty(userId, diff);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid difficulty! Use EASY, MEDIUM or HARD");
        }
    }
}