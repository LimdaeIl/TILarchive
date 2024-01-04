package com.jump.park.repository;

import com.jump.park.answer.repository.AnswerRepository;
import com.jump.park.question.entity.Question;
import com.jump.park.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void testJpa() {
        Question question = new Question();
        question.setSubject("첫 번째 글입니다.");
        question.setContent("첫 번째 글 내용입니다.");
        question.setCreatedAt(LocalDateTime.now());
        questionRepository.save(question);
    }

}