package com.coura.dao;

import com.coura.model.StudentCourse;

import java.util.List;

import com.coura.model.Course;

public interface RegistrationDao {
	
	public void insertRegistrationDetails(StudentCourse sc);
	public List<Course> getEnrolledCourses(String id);
	public void unEnrollCourse(String id, Integer courseId);
}
