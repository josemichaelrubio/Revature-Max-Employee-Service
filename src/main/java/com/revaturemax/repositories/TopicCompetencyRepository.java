package com.revaturemax.repositories;

import com.revaturemax.models.Employee;
import com.revaturemax.models.Notes;
import com.revaturemax.models.TopicCompetency;
import com.revaturemax.models.TopicCompetencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicCompetencyRepository extends JpaRepository<TopicCompetency, TopicCompetencyId> {

    @Query("select tc from TopicCompetency tc left join fetch tc.employee left join fetch tc.favNotes where tc.employee = :employee")
    List<TopicCompetency> findByEmployee(Employee employee);

    @Query("SELECT tc FROM TopicCompetency tc " +
           "LEFT JOIN FETCH tc.employee AS e " +
            "LEFT JOIN FETCH tc.favNotes " +
           "WHERE e IN :employees")
    List<TopicCompetency> findByEmployeeIn(List<Employee> employees);

    public int countByFavNotes(Notes favNotes);
}
