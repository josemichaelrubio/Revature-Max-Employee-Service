package com.revaturemax.models;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonFilter("Notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "topic_id")
    private Long topicId;

    @Lob
    private String content;

    public Notes() {}

    public Notes(Employee employee, Long topicId, String content) {
        this.employee = employee;
        this.topicId = topicId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(id, notes.id) && Objects.equals(employee, notes.employee) && Objects.equals(topicId, notes.topicId) && Objects.equals(content, notes.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, topicId, content);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", employee=" + employee +
                ", topic=" + topicId +
                ", content='" + content + '\'' +
                '}';
    }
}
