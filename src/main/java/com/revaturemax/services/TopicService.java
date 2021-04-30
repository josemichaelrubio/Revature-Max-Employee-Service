package com.revaturemax.services;

import com.revaturemax.models.*;
import com.revaturemax.repositories.TopicCompetencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicCompetencyRepository topicCompetencyRepository;

    public ResponseEntity<String> setEmployeeTopic(Token token, long employeeId, long topicId,
                                                   TopicCompetency topicCompetency)
    {
        /*if (token.getEmployeeId() != employeeId) return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        if (!topicRepository.existsById(topicId)) return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        topicCompetency.setEmployee(new Employee(employeeId));
        topicCompetency.setTopic(new Topic(topicId));
        topicCompetencyRepository.save(topicCompetency);*/
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
