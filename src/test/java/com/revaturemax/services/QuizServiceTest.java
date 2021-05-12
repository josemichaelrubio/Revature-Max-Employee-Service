package com.revaturemax.services;


import com.revaturemax.models.Employee;
import com.revaturemax.models.QuizScore;
import com.revaturemax.models.QuizScoreId;
import com.revaturemax.repositories.QuizScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @InjectMocks
    QuizService quizService;

    @MockBean
    QuizScoreRepository quizScoreRepository;

    @Test
    public void setQuizScore_Test(){
        Employee employee = new Employee();
        employee.setId(1L);
        QuizScoreId quizScoreId = new QuizScoreId(1L, 2L);
        QuizScore quizScore = new QuizScore(quizScoreId, employee, 98.1f);
        when(quizScoreRepository.save(quizScore)).thenReturn(quizScore);
        assertEquals(new ResponseEntity<>(HttpStatus.OK), quizService.setQuizScore(1L, 2L, quizScore));
    }

}
