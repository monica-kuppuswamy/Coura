package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "INSTRUCTORREVIEW")
public class InstructorReview implements Serializable {
	
	@Id
	@Column(name = "emailId")
	private String userEmailId;
	
	@Id
	@Column(name = "instructor_id")
	private int instructorId;
	
	@Column(name = "review")
	private String review;
	
	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
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
        InstructorReview cr = (InstructorReview) o;
        return userEmailId == cr.userEmailId && instructorId == cr.instructorId && review == cr.review;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(userEmailId, instructorId, review);
	}
}
