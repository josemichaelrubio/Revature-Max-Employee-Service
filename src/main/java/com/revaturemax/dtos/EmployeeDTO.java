package com.revaturemax.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revaturemax.models.Employee;

import java.util.List;

public class EmployeeDTO {

    private Employee employee;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EmployeeQuizScore> quizScores;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EmployeeTopicCompetency> topicCompetencies;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EmployeeQCFeedback> qcFeedbacks;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<EmployeeQuizScore> getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(List<EmployeeQuizScore> quizScores) {
        this.quizScores = quizScores;
    }

    public List<EmployeeTopicCompetency> getTopicCompetencies() {
        return topicCompetencies;
    }

    public void setTopicCompetencies(List<EmployeeTopicCompetency> topicCompetencies) {
        this.topicCompetencies = topicCompetencies;
    }

    public List<EmployeeQCFeedback> getQcFeedbacks() {
        return qcFeedbacks;
    }

    public void setQcFeedbacks(List<EmployeeQCFeedback> qcFeedbacks) {
        this.qcFeedbacks = qcFeedbacks;
    }
}
