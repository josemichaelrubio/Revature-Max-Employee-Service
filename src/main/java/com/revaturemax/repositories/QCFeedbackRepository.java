package com.revaturemax.repositories;

import com.revaturemax.models.Employee;
import com.revaturemax.models.QCFeedback;
import com.revaturemax.models.QCFeedbackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QCFeedbackRepository extends JpaRepository<QCFeedback, QCFeedbackId> {

    List<QCFeedback> findByEmployee(Employee employee);

    @Query("SELECT qcf FROM QCFeedback qcf " +
            "LEFT JOIN FETCH qcf.employee AS e " +
            "WHERE e IN :employees")
    List<QCFeedback> findByEmployeeIn(List<Employee> employees);

}
