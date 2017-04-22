package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STUDENTCOURSE")
public class StudentCourse implements Serializable {
	@Id
	@Column(name = "emailId")
	private String userEmailId;
	
	@Id
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "instructor_id")
	private int instructorId;

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

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	
	@Override
    public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof StudentCourse)) {
            return false;
        }
        StudentCourse cr = (StudentCourse) o;
        return userEmailId == cr.userEmailId && courseId == cr.courseId && instructorId == cr.instructorId;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(userEmailId, courseId, instructorId);
	}
}