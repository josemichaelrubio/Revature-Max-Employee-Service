package com.revaturemax.services;

import com.revaturemax.models.Employee;
import com.revaturemax.models.QCFeedback;
import com.revaturemax.models.QCFeedbackId;
import com.revaturemax.repositories.QCFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QCService {

    @Autowired
    private QCFeedbackRepository qcFeedbackRepository;

    public ResponseEntity<String> setQCFeedback(long employeeId, long qcId, QCFeedback qcFeedback) {
        qcFeedback.setId(new QCFeedbackId(employeeId, qcId));
        qcFeedback.setEmployee(new Employee(employeeId));
        qcFeedbackRepository.save(qcFeedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
