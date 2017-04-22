package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "COURSEINSTRUCTOR")
public class CourseInstructor implements Serializable {
	
	@Id
	@Column(name = "course_id")
	private int courseId;
	
	@Id
	@Column(name = "instructor_id")
	private int instructorId;

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
        if (!(o instanceof CourseInstructor)) {
            return false;
        }
        CourseInstructor ci = (CourseInstructor) o;
        return courseId == ci.courseId && instructorId == ci.instructorId;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(courseId, instructorId);
	}
}
