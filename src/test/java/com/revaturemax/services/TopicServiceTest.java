package com.revaturemax.services;

import com.revaturemax.models.Employee;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetency;
import com.revaturemax.models.TopicCompetencyId;
import com.revaturemax.repositories.TopicCompetencyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TopicServiceTest {

    @InjectMocks
    private TopicService topicService;
    @MockBean
    private TopicCompetencyRepository topicCompetencyRepo;

    @Test
    public void testSetTopicCompetency() {
        TopicCompetency tc = new TopicCompetency();
        tc.setId(new TopicCompetencyId(1l, 1l));
        tc.setEmployee(new Employee(1l));
        tc.setFavNotes(new Notes(1l));
        when(topicCompetencyRepo.save(tc)).thenReturn(tc);

        TopicCompetency res = topicService.setEmployeeTopicCompetency(1l, 1l, tc);

        assertAll(
                () -> assertEquals(tc.getEmployee().getId(), res.getEmployee().getId()),
                () -> assertEquals(tc.getFavNotes().getId(), res.getFavNotes().getId()),
                () -> assertEquals(tc.getId().getTopicId(), res.getId().getTopicId())
        );
    }
}
