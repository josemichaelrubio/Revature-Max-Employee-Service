package com.revaturemax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.revaturemax.models.*;
import com.revaturemax.repositories.*;
import com.revaturemax.repositories.QuizScoreRepository;
import com.revaturemax.util.Passwords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    QuizScoreRepository quizScoreRepository;
    @Autowired
    TopicCompetencyRepository topicCompetencyRepository;

    public ResponseEntity<String> getEmployeeInfo(Token token, long employeeId)
    {
        if (token.getEmployeeId() != employeeId) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return null;
        // return writeEmployeeResponse(response);
    }

    @Transactional
    public ResponseEntity<String> createNewEmployee(String name, String email, String password)
    {
        if (!validName(name)) return new ResponseEntity<String>("Bad name", HttpStatus.BAD_REQUEST);
        if (!validEmail(email)) return new ResponseEntity<String>("Bad email", HttpStatus.BAD_REQUEST);
        if (!validPassword(password)) return new ResponseEntity<String>("Bad password", HttpStatus.BAD_REQUEST);

        Employee employee = new Employee(); // need to consider making employee with another constructor
        try {
            employee = employeeRepository.save(employee);
        } catch (UnexpectedRollbackException e) {
            return new ResponseEntity<String>("The provided email is already taken", HttpStatus.CONFLICT);
        }

        byte[] salt = Passwords.getNewPasswordSalt();
        byte[] hash = Passwords.getPasswordHash(password, salt);
        passwordRepository.save(new Password(employee, salt, hash));
        try {
            return new ResponseEntity<String>(objectMapper.writer().writeValueAsString(employee), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }*/

    private boolean validName(String name) {
        return name != null && !name.equals("") && name.length() < 256;
    }

    private boolean validEmail(String email) {
        if (email == null) return false;
        return email.matches("^\\S+@\\S+\\.\\S+$") && email.length() < 256;
    }

    private boolean validPassword(String password) {
        return password != null && !password.equals("");
    }

}
