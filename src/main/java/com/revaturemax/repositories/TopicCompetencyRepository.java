package com.revaturemax.repositories;

import com.revaturemax.models.Employee;
import com.revaturemax.models.TopicCompetency;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicCompetencyRepository extends JpaRepository<TopicCompetency, TopicCompetencyId> {

//    @Query("SELECT et FROM EmployeeTopic et LEFT JOIN FETCH et.topic t LEFT JOIN FETCH t.tag WHERE et.employee = :employee")
//    List<TopicCompetency> findByEmployeeEquals(@Param("employee") Employee employee);
//
//    @Query("SELECT et FROM EmployeeTopic et LEFT JOIN FETCH et.employee WHERE et.id = :id")
//    TopicCompetency getEmployeeTopicById(@Param("id") Long id);
}
