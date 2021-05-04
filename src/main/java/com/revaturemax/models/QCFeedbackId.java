package com.revaturemax.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QCFeedbackId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "qc_id")
    private Long qcId;

    public QCFeedbackId() {}

    public QCFeedbackId(Long employeeId, Long qcId) {
        this.employeeId = employeeId;
        this.qcId = qcId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getQcId() {
        return qcId;
    }

    public void setQcId(Long qcId) {
        this.qcId = qcId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QCFeedbackId that = (QCFeedbackId) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(qcId, that.qcId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, qcId);
    }

}
