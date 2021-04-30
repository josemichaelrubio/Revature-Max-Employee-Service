package com.revaturemax.repositories;

import com.revaturemax.models.Employee;
import com.revaturemax.models.QuizScore;
import com.revaturemax.models.QuizScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizScoreRepository extends JpaRepository<QuizScore, QuizScoreId> {

    @Query("SELECT qs FROM QuizScore qs " +
            "LEFT JOIN FETCH qs.employee AS e " +
            "WHERE e IN :employees")
    List<QuizScore> findByEmployeeIn(List<Employee> employees);

}
