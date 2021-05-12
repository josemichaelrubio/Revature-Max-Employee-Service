package com.revaturemax.dtos;

import com.revaturemax.models.QCFeedbackId;

import java.util.Objects;

public class EmployeeQCFeedback {

	private QCFeedbackId id;
	private Integer associateRating;
	private Integer instructorFeedback;
	private String name;

	public EmployeeQCFeedback() {}

	public EmployeeQCFeedback(QCFeedbackId id, Integer associateRating, Integer instructorFeedback, String name) {
		this.id = id;
		this.associateRating = associateRating;
		this.instructorFeedback = instructorFeedback;
		this.name = name;
	}

	public EmployeeQCFeedback(QCFeedbackId id, Integer associateRating, Integer instructorFeedback) {
		this.id = id;
		this.associateRating = associateRating;
		this.instructorFeedback = instructorFeedback;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QCFeedbackId getId() {
		return id;
	}

	public void setId(QCFeedbackId id) {
		this.id = id;
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
		EmployeeQCFeedback that = (EmployeeQCFeedback) o;
		return Objects.equals(id, that.id) && Objects.equals(associateRating, that.associateRating) && Objects.equals(instructorFeedback, that.instructorFeedback);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, associateRating, instructorFeedback);
	}

	@Override
	public String toString() {
		return "QCFeedback{" +
				"id=" + id +
				", associateRating=" + associateRating +
				", instructorFeedback=" + instructorFeedback +
				'}';
	}


}
