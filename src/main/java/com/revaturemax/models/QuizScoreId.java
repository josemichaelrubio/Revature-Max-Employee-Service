package com.revaturemax.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuizScoreId implements Serializable {
    @Column(name="employee_id")
    private Long employeeId;
    @Column(name="quiz_id")
    private Long quizId;

    public QuizScoreId() {
        super();
    }

    public QuizScoreId(Long employeeId, Long quizId) {
        this.employeeId = employeeId;
        this.quizId = quizId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizScoreId that = (QuizScoreId) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(quizId, that.quizId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, quizId);
    }

    @Override
    public String toString() {
        return "QuizScoreId{" +
                "employeeId=" + employeeId +
                ", quizId=" + quizId +
                '}';
    }
}
