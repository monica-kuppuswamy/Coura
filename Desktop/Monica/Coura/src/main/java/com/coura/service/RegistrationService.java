package com.coura.service;

import java.util.List;

import com.coura.model.Course;
import com.coura.model.StudentCourse;

public interface RegistrationService {
	public void insertRegistrationDetails(StudentCourse sc);
	public List<Course> getEnrolledCourses(String id);
	public void unEnrollCourse(String id, Integer courseId);
}
