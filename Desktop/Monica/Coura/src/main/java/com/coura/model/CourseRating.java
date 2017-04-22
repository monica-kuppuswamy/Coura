package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "COURSERATING")
public class CourseRating implements Serializable {

	@Id
	@Column(name = "emailId")
	private String userEmailId;
	
	@Id
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "difficulty_rating")
	private Integer difficultyRating;
	
	@Column(name = "usefulness_rating")
	private Integer usefulnessRating;

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Integer getDifficultyRating() {
		return difficultyRating;
	}

	public void setDifficultyRating(Integer difficultyRating) {
		this.difficultyRating = difficultyRating;
	}

	public Integer getUsefulnessRating() {
		return usefulnessRating;
	}

	public void setUsefulnessRating(Integer usefulnessRating) {
		this.usefulnessRating = usefulnessRating;
	}
	
	@Override
    public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof CourseRating)) {
            return false;
        }
        CourseRating cr = (CourseRating) o;
        return userEmailId == cr.userEmailId && courseId == cr.courseId && difficultyRating == cr.difficultyRating &&
        		usefulnessRating == cr.usefulnessRating;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(userEmailId, courseId, difficultyRating, usefulnessRating);
	}
}
