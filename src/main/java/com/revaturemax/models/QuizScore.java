package com.revaturemax.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "quiz_score")
@Component
@JsonIgnoreProperties("hibernateLazyInitializer")
@Scope("prototype")
public class QuizScore {

    @EmbeddedId
    private QuizScoreId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name="employee_id")
    private Employee employee;

    private float score;

    public QuizScore() {
        super();
    }

    public QuizScore(QuizScoreId id, Employee employee, float score) {
        this.id = id;
        this.employee = employee;
        this.score = score;
    }

    public QuizScoreId getId() {
        return id;
    }

    public void setId(QuizScoreId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizScore quizScore = (QuizScore) o;
        return Float.compare(quizScore.score, score) == 0 && Objects.equals(id, quizScore.id) && Objects.equals(employee, quizScore.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, score);
    }

    @Override
    public String toString() {
        return "QuizScore{" +
                "id=" + id +
                ", employee=" + employee +
                ", score=" + score +
                '}';
    }
}
