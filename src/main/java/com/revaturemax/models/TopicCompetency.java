package com.revaturemax.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_competency")
public class TopicCompetency {

    @EmbeddedId
    private TopicCompetencyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fav_notes_id")
    private Notes favNotes;

    private Float competency;

    public TopicCompetency() {
        super();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Notes getFavNotes() {
        return favNotes;
    }

    public void setFavNotes(Notes favNotes) {
        this.favNotes = favNotes;
    }

    public Float getCompetency() {
        return competency;
    }

    public void setCompetency(Float competency) {
        this.competency = competency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicCompetency that = (TopicCompetency) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(favNotes, that.favNotes) && Objects.equals(competency, that.competency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, favNotes, competency);
    }

    @Override
    public String toString() {
        return "TopicCompetency{" +
                "id=" + id +
                ", employee=" + employee +
                ", favNotes=" + favNotes +
                ", competency=" + competency +
                '}';
    }
}
