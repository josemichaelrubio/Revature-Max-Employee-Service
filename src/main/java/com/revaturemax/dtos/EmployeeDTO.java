package com.revaturemax.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revaturemax.models.Employee;
import com.revaturemax.models.QCFeedback;
import com.revaturemax.models.QuizScore;
import com.revaturemax.models.TopicCompetency;

import java.util.List;

public class EmployeeDTO {

    private Employee employee;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<QuizScore> quizScores;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TopicCompetency> topicCompetencies;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<QCFeedback> qcFeedbacks;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<QuizScore> getQuizScores() {
        return quizScores;
    }

    public void setQuizScores(List<QuizScore> quizScores) {
        this.quizScores = quizScores;
    }

    public List<TopicCompetency> getTopicCompetencies() {
        return topicCompetencies;
    }

    public void setTopicCompetencies(List<TopicCompetency> topicCompetencies) {
        this.topicCompetencies = topicCompetencies;
    }

    public List<QCFeedback> getQcFeedbacks() {
        return qcFeedbacks;
    }

    public void setQcFeedbacks(List<QCFeedback> qcFeedbacks) {
        this.qcFeedbacks = qcFeedbacks;
    }

}
