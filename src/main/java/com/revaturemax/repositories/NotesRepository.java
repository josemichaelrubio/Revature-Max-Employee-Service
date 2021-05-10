package com.revaturemax.repositories;
import com.revaturemax.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

//    @Query("SELECT n FROM Notes n LEFT JOIN FETCH n.employee LEFT JOIN FETCH n.topic WHERE n.employee.id = :employeeId AND n.topic.id = :topicId")
//    public Notes getNotesByEmployeeIdAndTopicId(@Param("employeeId") long employeeId, @Param("topicId") long topicId);


    @Query("SELECT tc.favNotes.id FROM TopicCompetency tc WHERE tc.id.topicId = :topicId AND tc.employee.id = :employeeId")
    Long getFavNotes(@Param("employeeId") long employeeId, @Param("topicId") long topicId);

//    @Query("select n \n" +
//            "from Notes n left join \n" +
//            "   (select tc.favNotes, count(tc.favNotes) as n.votes \n" +
//            "   from TopicCompetency tc \n" +
//            "   where tc.id.topicId = :topicId \n" +
//            "   group by tc.favNotes) as vote_count \n" +
//            "on n.id = vote_count.id")
    public List<Notes> getNotesByTopicId(@Param("topicId") Long topicId);

}

//(select post_id, count(post_id) as votes
//        from users_posts
//        group by post_id) as vote_count
//        on p.id = vote_count.post_id;