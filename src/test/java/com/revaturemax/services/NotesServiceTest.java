package com.revaturemax.services;

import com.revaturemax.models.Employee;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetency;
import com.revaturemax.models.TopicCompetencyId;
import com.revaturemax.repositories.NotesRepository;
import com.revaturemax.repositories.TopicCompetencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @InjectMocks
    private NotesService notesService;
    @MockBean
    private NotesRepository notesRepo;
    @MockBean
    private TopicCompetencyRepository topicCompetencyRepo;

}
