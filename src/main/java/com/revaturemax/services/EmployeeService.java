package com.revaturemax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revaturemax.dtos.EmployeeDTO;
import com.revaturemax.models.*;
import com.revaturemax.repositories.*;
import com.revaturemax.util.Passwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private QuizScoreRepository quizScoreRepository;
    @Autowired
    private TopicCompetencyRepository topicCompetencyRepository;
    @Autowired
    private QCFeedbackRepository qcFeedbackRepository;
    @Autowired
    private EmailService emailService;

    public ResponseEntity<String> getEmployee(long employeeId, Set<String> fields)
    {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        if (fields != null && fields.contains("quiz-scores")) {
            employeeDTO.setQuizScores(quizScoreRepository.findByEmployee(employee));
        }
        if (fields != null && fields.contains("topic-competencies")) {
            employeeDTO.setTopicCompetencies(topicCompetencyRepository.findByEmployee(employee));
        }
        if (fields != null && fields.contains("qc-feedbacks")) {
            employeeDTO.setQcFeedbacks(qcFeedbackRepository.findByEmployee(employee));
        }

        try {
            return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employeeDTO), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public ResponseEntity<String> getEmployees(Set<String> emails) {
        List<Employee> employees = employeeRepository.findByEmailIn(emails);
        try {
            return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employees), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getEmployees(Set<Long> employeeIds, Set<String> fields) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);

        List<QuizScore> quizScores = null;
        if (fields != null && fields.contains("quiz-scores")) {
            quizScores = quizScoreRepository.findByEmployeeIn(employees);
        }
        List<TopicCompetency> topicCompetencies = null;
        if (fields != null && fields.contains("topic-competencies")) {
            topicCompetencies = topicCompetencyRepository.findByEmployeeIn(employees);
        }
        List<QCFeedback> qcFeedbacks = null;
        if (fields != null && fields.contains("qc-feedbacks")) {
            qcFeedbacks = qcFeedbackRepository.findByEmployeeIn(employees);
        }

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            if (quizScores != null && !quizScores.isEmpty()) {
                employeeDTO.setQuizScores(quizScores.stream()
                        .filter(x -> x.getEmployee().equals(employee))
                        .collect(Collectors.toList()));
            }
            if (topicCompetencies != null && !topicCompetencies.isEmpty()) {
                employeeDTO.setTopicCompetencies(topicCompetencies.stream()
                        .filter(x -> x.getEmployee().equals(employee))
                        .collect(Collectors.toList()));
            }
            if (qcFeedbacks != null && !qcFeedbacks.isEmpty()) {
                employeeDTO.setQcFeedbacks(qcFeedbacks.stream()
                        .filter(x -> x.getEmployee().equals(employee))
                        .collect(Collectors.toList()));
            }
            employeeDTOs.add(employeeDTO);
        }

        try {
            return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employeeDTOs), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> createNewEmployee(String name, String email, String password)
    {
        if (!validName(name)) return new ResponseEntity<>("Bad name", HttpStatus.BAD_REQUEST);
        if (!validEmail(email)) return new ResponseEntity<>("Bad email", HttpStatus.BAD_REQUEST);
        if (!validPassword(password)) return new ResponseEntity<>("Bad password", HttpStatus.BAD_REQUEST);

        Employee employee = new Employee(name, email);
        //EMPLOYEE STARTS OFF AS A GUEST. WILL REMAIN A GUEST UNTIL EMAIL IS VERIFIED
        employee.setRole(Role.GUEST);
        try {
            employee = employeeRepository.save(employee);
            //Todo add correct link to server
            String link = "http://localhost:8082/verify/" + employee.getId();
            emailService.sendEmail(employee.getEmail(), "Verification Link", link);
        } catch (UnexpectedRollbackException e) {
            return new ResponseEntity<>("The provided email is already taken", HttpStatus.CONFLICT);
        }

        byte[] salt = Passwords.getNewPasswordSalt();
        byte[] hash = Passwords.getPasswordHash(password, salt);
        passwordRepository.save(new Password(employee, salt, hash));
        try {
            return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employee), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> updateEmployee(Long employeeId, Employee employee) {
        employee.setId(employeeId);
        employee = employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

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
