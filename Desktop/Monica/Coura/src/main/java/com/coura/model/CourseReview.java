package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "COURSEREVIEW")
public class CourseReview implements Serializable {
	
	@Id
	@Column(name = "emailId")
	private String userEmailId;
	
	@Id
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "review")
	private String review;

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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	@Override
    public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof CourseReview)) {
            return false;
        }
        CourseReview cr = (CourseReview) o;
        return userEmailId == cr.userEmailId && courseId == cr.courseId && review == cr.review;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(userEmailId, courseId, review);
	}
}
