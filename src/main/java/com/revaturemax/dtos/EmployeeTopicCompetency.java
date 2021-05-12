package com.revaturemax.dtos;

import com.revaturemax.models.TopicCompetencyId;

import java.util.Objects;

public class EmployeeTopicCompetency {
	private TopicCompetencyId id;
	// unsure on the type
	private float competency;
	private String techName;

	public EmployeeTopicCompetency() {
	}

	public EmployeeTopicCompetency(TopicCompetencyId id, float competency, String name) {
		this.id = id;
		this.competency = competency;
		this.techName = name;
	}

	public EmployeeTopicCompetency(TopicCompetencyId id, float competency) {
		this.id = id;
		this.competency = competency;
	}
// favNotes would go here if we need it

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public TopicCompetencyId getId() {
		return id;
	}

	public void setId(TopicCompetencyId id) {
		this.id = id;
	}

	public float getCompetency() {
		return competency;
	}

	public void setCompetency(float competency) {
		this.competency = competency;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeeTopicCompetency that = (EmployeeTopicCompetency) o;
		return Float.compare(that.competency, competency) == 0 && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, competency);
	}

	@Override
	public String toString() {
		return "EmployeeTopicCompetency{" +
				"id=" + id +
				", competency=" + competency +
				'}';
	}

}

