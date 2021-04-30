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

    public ResponseEntity<String> setEmployeeQuiz(Token token, long employeeId, long quizId, QuizScore quizScore) {
        /*if (token.getEmployeeId() != employeeId) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        if (!quizRepository.existsById(quizId)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        quizScore.setEmployee(new Employee(employeeId));
        quizScore.setQuiz(new Quiz(quizId));
        employeeQuizRepository.save(quizScore);*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
