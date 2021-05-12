package com.revaturemax.controllers;

import com.revaturemax.dtos.NotesDTO;
import com.revaturemax.models.*;
import com.revaturemax.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    QCService qcService;
    @Autowired
    NotesService notesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEmployees(@RequestParam(value = "id", required = false) Set<Long> employeeIds,
                                               @RequestParam(value = "email", required = false) Set<String> emails,
                                               @RequestParam(value = "field", required = false) Set<String> fields)
    {
        logger.info("GET /employees/ received");
        if (employeeIds != null) return employeeService.getEmployees(employeeIds, fields);
        if (emails != null) return employeeService.getEmployees(emails);
        return new ResponseEntity<>("Querying without either 'id' or 'email' parameter not allowed",
                HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> createNewEmployee(@RequestParam("name") String name,
                                         @RequestParam("email") String email,
                                         @RequestParam("password") String password)
    {
        logger.info("POST /employees received");
        return employeeService.createNewEmployee(name, email, password);
    }

    @GetMapping(path = "/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employee-id") long employeeId)
    {
        logger.info("GET /employees/{} received", employeeId);
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping(path = "/{employee-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employee-id") long employeeId,
                                                 @RequestBody Employee employee)
    {
        logger.info("Updating an employee, id = {}", employeeId);
        return employeeService.updateEmployee(employeeId, employee);
    }

    @PutMapping(path = "/{employee-id}/quizzes/{quiz-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setQuizScore(@PathVariable("employee-id") long employeeId,
                                           @PathVariable("quiz-id") long quizId,
                                           @RequestBody QuizScore quizScore)
    {
        logger.info("PUT /employees/{}/quizzes/{} received", employeeId, quizId);
        quizService.setQuizScore(employeeId, quizId, quizScore);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{employee-id}/topics/{topic-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setTopicCompetency(@PathVariable("employee-id") long employeeId,
                                          @PathVariable("topic-id") long topicId,
                                          @RequestBody TopicCompetency topicCompetency)
    {
        logger.info("PUT /employees/{}/topics/{} received", employeeId, topicId);
        topicService.setEmployeeTopicCompetency(employeeId, topicId, topicCompetency);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{employee-id}/qcs/{qc-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setQCFeedback(@PathVariable("employee-id") long employeeId,
                                                             @PathVariable("qc-id") long qcId,
                                                             @RequestBody QCFeedback qcFeedback)
    {
        logger.info("PUT /employees/{}/qcs/{} received", employeeId, qcId);
        return qcService.setQCFeedback(employeeId, qcId, qcFeedback);
    }

    @GetMapping("/notes/{topic-id}")
    public ResponseEntity<NotesDTO> getNotesByTopic(@PathVariable("topic-id") Long topicId,
                                                    @RequestParam("employee") Long employeeId) {
        return new ResponseEntity<>(notesService.getNotesByTopic(topicId, employeeId), HttpStatus.OK);
    }

    @PutMapping(path = "/{employee-id}/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setNotes(@PathVariable("employee-id") long employeeId,
                                           @RequestBody Notes notes)
    {
        logger.info("PUT /employees/{}/notes received", employeeId);
        System.out.println(notes);
        return notesService.setNotes(employeeId, notes);
    }



//    @GetMapping(path = "/{employee-id}/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> getFavoriteNotes(@PathVariable("employee-id") long employeeId,
//                                                   @RequestBody FavNotesDTO favNotesDTO)
//    {
//
//        logger.info("Getting favorite notes with id: ", favNotesId);
//        return notesService.getFavNotes(favNotesId, favNotesDTO);
//    }

}
