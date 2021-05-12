package com.revaturemax.dtos;

import com.revaturemax.models.QuizScoreId;

import java.util.Objects;

public class EmployeeQuizScore {
	private QuizScoreId id;
	private float score;
	private String name;

	public EmployeeQuizScore(QuizScoreId id, float score, String name) {
		this.id = id;
		this.score = score;
		this.name = name;
	}

	public EmployeeQuizScore(QuizScoreId id, float score) {
		this.id = id;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuizScoreId getId() {
		return id;
	}

	public void setId(QuizScoreId id) {
		this.id = id;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeeQuizScore that = (EmployeeQuizScore) o;
		return Float.compare(that.score, score) == 0 && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, score);
	}

	@Override
	public String toString() {
		return "EmployeeQuizScore{" +
				"id=" + id +
				", score=" + score +
				'}';
	}

}

