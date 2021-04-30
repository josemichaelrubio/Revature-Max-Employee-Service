package com.revaturemax.services;

import com.revaturemax.models.*;
import com.revaturemax.repositories.QuizScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizScoreRepository quizScoreRepository;

    public ResponseEntity<String> setQuizScore(long employeeId, long quizId, QuizScore quizScore) {
        quizScore.setId(new QuizScoreId(employeeId, quizId));
        quizScore.setEmployee(new Employee(employeeId));
        quizScoreRepository.save(quizScore);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
