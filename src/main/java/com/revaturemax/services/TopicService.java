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
    private TopicCompetencyRepository topicCompetencyRepo;

    public TopicCompetency setEmployeeTopicCompetency(long employeeId, long topicId,
                                                   TopicCompetency topicCompetency)
    {
        topicCompetency.setId(new TopicCompetencyId(employeeId, topicId));
        topicCompetency.setEmployee(new Employee(employeeId));
        return topicCompetencyRepo.save(topicCompetency);
    }
}
