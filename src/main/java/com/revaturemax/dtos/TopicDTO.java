package com.revaturemax.dtos;

import java.util.Objects;

public class TopicDTO {

	private long topicId;
	private String techName;
	private Float competency;

	public TopicDTO() {
	}

	public TopicDTO(long topicId, String techName) {
		this.topicId = topicId;
		this.techName = techName;
	}

	public TopicDTO(long topicId, Float competency) {
		this.topicId = topicId;
		this.competency = competency;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
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
		TopicDTO topicDTO = (TopicDTO) o;
		return topicId == topicDTO.topicId && Objects.equals(techName, topicDTO.techName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(topicId, techName);
	}

	@Override
	public String toString() {
		return "TopicDTO{" +
				"topicId=" + topicId +
				", techName='" + techName + '\'' +
				'}';
	}
}
