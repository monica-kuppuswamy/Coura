package com.coura.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSES")
public class Course {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "course_number")
	private String courseNumber;
	
	@Column(name = "course_name")
	private String courseName;
	
	public Course(Integer id, String courseNumber, String courseName, String prerequisite, String description) {
		super();
		this.id = id;
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.prerequisite = prerequisite;
		this.description = description;
	}

	@Column(name = "course_prerequisite")
	private String prerequisite;
	
	@Column(name = "course_description")
	private String description;

	public Course() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
