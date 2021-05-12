package com.revaturemax.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revaturemax.models.Employee;

import java.util.List;

public class EmployeeDTO {

    private Employee employee;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<QuizDTO> quizScores;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TopicDTO> topicCompetencies;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<QCDTO> qcFeedbacks;

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

    public List<QuizDTO> getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(List<QuizDTO> quizScores) {
        this.quizScores = quizScores;
    }

    public List<TopicDTO> getTopicCompetencies() {
        return topicCompetencies;
    }

    public void setTopicCompetencies(List<TopicDTO> topicCompetencies) {
        this.topicCompetencies = topicCompetencies;
    }

    public List<QCDTO> getQcFeedbacks() {
        return qcFeedbacks;
    }

    public void setQcFeedbacks(List<QCDTO> qcFeedbacks) {
        this.qcFeedbacks = qcFeedbacks;
    }
}
