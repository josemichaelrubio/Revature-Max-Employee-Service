package com.revaturemax.dtos;

import java.util.Objects;

public class QCDTO {

	private Long id;
	private String name;
	private Integer associateRating;
	private Integer instructorFeedback;

	public QCDTO() { }

	public QCDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public QCDTO(Long id, Integer associateRating, Integer instructorFeedback) {
		this.id = id;
		this.associateRating = associateRating;
		this.instructorFeedback = instructorFeedback;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QCDTO qcdto = (QCDTO) o;
		return Objects.equals(id, qcdto.id) && Objects.equals(name, qcdto.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "QCDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
