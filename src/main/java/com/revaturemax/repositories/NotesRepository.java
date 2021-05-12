package com.revaturemax.repositories;

import com.revaturemax.dtos.NotesDTO;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {

//    @Query("SELECT n FROM Notes n LEFT JOIN FETCH n.employee LEFT JOIN FETCH n.topic WHERE n.employee.id = :employeeId AND n.topic.id = :topicId")
//    public Notes getNotesByEmployeeIdAndTopicId(@Param("employeeId") long employeeId, @Param("topicId") long topicId);

    public List<Notes> getNotesByTopicId(@Param("topicId") Long topicId);
}