package com.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tracker.entity.Question;
import com.tracker.service.QuestionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired QuestionService questionService;

    @PostMapping("/add")
    public Question add(@RequestBody Question q) {
        return questionService.addQuestion(q);
    }

    @GetMapping("/stats/{userId}")
    public Map<String, Long> stats(@PathVariable Long userId) {
        return questionService.getStats(userId);
    }

    @GetMapping("/all/{userId}")
    public List<Question> all(@PathVariable Long userId) {
        return questionService.getAllByUser(userId);
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id) {
        return questionService.getById(id);
    }

    // ✅ Update
    @PutMapping("/update/{id}")
    public Question update(@PathVariable Long id, @RequestBody Question q) {
        return questionService.updateQuestion(id, q);
    }

    // ✅ Delete
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "Question deleted successfully";
    }

    // ✅ Filter by difficulty
    @GetMapping("/filter/{userId}")
    public List<Question> filter(@PathVariable Long userId,
                                 @RequestParam String difficulty) {
        return questionService.filterByDifficulty(userId, difficulty);
    }
}