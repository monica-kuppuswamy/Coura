package com.coura.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "INSTRUCTORRATING")
public class InstructorRating implements Serializable {

	@Id
	@Column(name = "emailId")
	private String userEmailId;
	
	@Id
	@Column(name = "instructor_id")
	private int instructorId;
	
	@Id
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "quality_of_teaching")
	private Integer qualityOfTeachingRating;
	
	@Column(name = "grading_style")
	private Integer gradingStyleRating;

	@Column(name = "leniency")
	private Integer leniencyRating;
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

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

	public Integer getQualityOfTeachingRating() {
		return qualityOfTeachingRating;
	}

	public void setQualityOfTeachingRating(Integer qualityOfTeachingRating) {
		this.qualityOfTeachingRating = qualityOfTeachingRating;
	}

	public Integer getGradingStyleRating() {
		return gradingStyleRating;
	}

	public void setGradingStyleRating(Integer gradingStyleRating) {
		this.gradingStyleRating = gradingStyleRating;
	}

	public Integer getLeniencyRating() {
		return leniencyRating;
	}

	public void setLeniencyRating(Integer leniencyRating) {
		this.leniencyRating = leniencyRating;
	}

	@Override
    public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof InstructorRating)) {
            return false;
        }
        InstructorRating ir = (InstructorRating) o;
        return userEmailId == ir.userEmailId && instructorId == ir.instructorId && qualityOfTeachingRating == ir.qualityOfTeachingRating &&
        		gradingStyleRating == ir.gradingStyleRating && leniencyRating == ir.leniencyRating;
	}
	
	@Override
    public int hashCode() { 
		return Objects.hash(userEmailId, instructorId, qualityOfTeachingRating, gradingStyleRating, leniencyRating);
	}
}
