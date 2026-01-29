package com.tracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tracker.entity.Question;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
 List<Question> findByUserId(Long userId);
}
