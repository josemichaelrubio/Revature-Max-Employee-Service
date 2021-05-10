package com.revaturemax.services;

import com.revaturemax.models.Employee;
import com.revaturemax.models.QCFeedback;
import com.revaturemax.models.QCFeedbackId;
import com.revaturemax.repositories.QCFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QCService {

    @Autowired
    private QCFeedbackRepository qcFeedbackRepository;

    public ResponseEntity<String> setQCRating(long employeeId, long qcId, Integer associateRating) {
        //TODO authorizate request userId = employeeId, validate qc exists by qcId
        QCFeedback feedback = qcFeedbackRepository.findById(new QCFeedbackId(employeeId, qcId)).orElse(null);
        if (feedback == null) {
            feedback = new QCFeedback();
            feedback.setId(new QCFeedbackId(employeeId, qcId));
            feedback.setEmployee(new Employee(employeeId));
        }
        feedback.setAssociateRating(associateRating);
        qcFeedbackRepository.save(feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> setQCFeedback(long employeeId, long qcId, Integer intructorFeedback) {
        //TODO authorizate request role = instructor, validate qc exists by qcId
        QCFeedback feedback = qcFeedbackRepository.findById(new QCFeedbackId(employeeId, qcId)).orElse(null);
        if (feedback == null) {
            feedback = new QCFeedback();
            feedback.setId(new QCFeedbackId(employeeId, qcId));
            feedback.setEmployee(new Employee(employeeId));
        }
        feedback.setInstructorFeedback(intructorFeedback);
        qcFeedbackRepository.save(feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
