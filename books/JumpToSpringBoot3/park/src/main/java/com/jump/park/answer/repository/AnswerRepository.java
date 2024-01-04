package com.jump.park.answer.repository;

import com.jump.park.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Question, Long> {
}
