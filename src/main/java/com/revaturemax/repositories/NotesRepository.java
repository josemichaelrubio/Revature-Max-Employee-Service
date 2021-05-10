package com.revaturemax.repositories;
;
import com.revaturemax.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

//    @Query("SELECT n FROM Notes n LEFT JOIN FETCH n.employee LEFT JOIN FETCH n.topic WHERE n.employee.id = :employeeId AND n.topic.id = :topicId")
//    public Notes getNotesByEmployeeIdAndTopicId(@Param("employeeId") long employeeId, @Param("topicId") long topicId);

    //We want to get the fav_notes_id
    @Query("SELECT tc.favNotes.id FROM TopicCompetency tc WHERE tc.id.topicId = :topicId AND tc.employee.id = :employeeId")
    Long getFavNotes(@Param("employeeId") long employeeId, @Param("topicId") long topicId);

}
