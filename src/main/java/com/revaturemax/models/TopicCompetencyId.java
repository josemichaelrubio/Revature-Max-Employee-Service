package com.revaturemax.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TopicCompetencyId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "topic_id")
    private Long topicId;

    public TopicCompetencyId() {
        super();
    }

    public TopicCompetencyId(Long employeeId, Long topicId) {
        this.employeeId = employeeId;
        this.topicId = topicId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicCompetencyId that = (TopicCompetencyId) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, topicId);
    }

    @Override
    public String toString() {
        return "TopicCompetencyId{" +
                "employeeId=" + employeeId +
                ", topicId=" + topicId +
                '}';
    }

}
