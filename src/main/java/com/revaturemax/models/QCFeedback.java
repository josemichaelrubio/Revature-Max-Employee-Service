package com.revaturemax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "qc_feedback")
public class QCFeedback {

    @EmbeddedId
    private QCFeedbackId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JsonIgnore
    private Employee employee;

    @Column(name = "associate_rating")
    private Integer associateRating;

    @Column(name = "instructor_feedback")
    private Integer instructorFeedback;

    public QCFeedbackId getId() {
        return id;
    }

    public void setId(QCFeedbackId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.id.setEmployeeId(employee.getId());
        this.employee = employee;
    }

    public Integer getAssociateRating() {
        return associateRating;
    }

    public void setAssociateRating(Integer associateRating) {
        this.associateRating = associateRating;
    }

    public Integer getInstructorFeedback() {
        return instructorFeedback;
    }

    public void setInstructorFeedback(Integer instructorFeedback) {
        this.instructorFeedback = instructorFeedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QCFeedback that = (QCFeedback) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) &&
                Objects.equals(associateRating, that.associateRating) &&
                Objects.equals(instructorFeedback, that.instructorFeedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, associateRating, instructorFeedback);
    }

}
