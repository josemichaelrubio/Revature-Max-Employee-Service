package com.revaturemax.controllers;

import com.revaturemax.services.EmployeeService;
import com.revaturemax.services.NotesService;
import com.revaturemax.services.QuizService;
import com.revaturemax.services.TopicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {

    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;
    @Autowired
    QuizService quizService;
    @Autowired
    TopicService topicService;
    @Autowired
    NotesService notesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEmployees(@RequestParam("id") Set<Long> employeeIds,
                                               @RequestParam("field") Set<String> fields)
    {
        logger.info("GET /employees/ received");
        return employeeService.getEmployees(employeeIds, fields);
        //return new ResponseEntity<>(employeeIds.toString() + fields.toString(), HttpStatus.OK);
    }

    /*@GetMapping(path = "/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEmployeeInfo(@PathVariable("employee-id") long employeeId,
                                                  @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        logger.info("GET /employees/{} received", employeeId);
        return employeeService.getEmployeeInfo(token, employeeId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> createNewEmployee(@RequestParam("name") String name,
                                         @RequestParam("email") String email,
                                         @RequestParam("password") String password)
    {
        logger.info("POST /employees received");
        return employeeService.createNewEmployee(name, email, password);
    }

    @DeleteMapping(path = "/{employee-id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employee-id") long employeeId,
                                          @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        logger.info("Deleting an employee with id: {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }*/

    /*@PutMapping(path = "/{employee-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@PathVariable("employee-id") long employeeId,
                                          @RequestBody Employee employee,
                                          @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        //TODO
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "/{employee-id}/quizzes/{quiz-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setEmployeeQuiz(@PathVariable("employee-id") long employeeId,
                                           @PathVariable("quiz-id") long quizId,
                                           @RequestBody QuizScore quizScore,
                                           @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        logger.info("PUT /employees/{}/quizzes/{} received", employeeId, quizId);
        quizScoreService.setEmployeeQuiz(token, employeeId, quizId, quizScore);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{employee-id}/topics/{topic-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setEmployeeTopic(@PathVariable("employee-id") long employeeId,
                                          @PathVariable("topic-id") long topicId,
                                          @RequestBody TopicCompetency topicCompetency,
                                          @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        logger.info("PUT /employees/{}/topics/{} received", employeeId, topicId);
        return topicService.setEmployeeTopic(token, employeeId, topicId, topicCompetency);
    }

    @PutMapping(path = "/{employee-id}/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setNotes(@PathVariable("employee-id") long employeeId,
                                           @RequestBody Notes notes,
                                           @RequestHeader("Authorization") String authorization)
    {
        Token token = Tokens.parseToken(authorization);
        if (token == null) return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        logger.info("PUT /employees/{}/notes received", employeeId);
        return notesService.setNotes(token, employeeId, notes);
    }*/

}
