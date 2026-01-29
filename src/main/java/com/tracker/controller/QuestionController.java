package com.tracker.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.tracker.repository.QuestionRepository;
import com.tracker.entity.Question;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

 @Autowired QuestionRepository repo;

 @PostMapping("/add")
 public Question add(@RequestBody Question q) {
  return repo.save(q);
 }

 @GetMapping("/stats/{userId}")
 public Map<String, Long> stats(@PathVariable Long userId) {
  return repo.findByUserId(userId).stream()
   .collect(Collectors.groupingBy(
    q -> q.getDifficulty().name(),
    Collectors.counting()
   ));
 }
}
