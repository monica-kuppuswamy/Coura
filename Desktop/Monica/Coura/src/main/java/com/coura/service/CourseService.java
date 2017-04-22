package com.coura.service;

import java.util.List;

import com.coura.model.Course;
import com.coura.model.Instructor;

public interface CourseService {
	
	public Course findCourseByNumber(Integer courseId);
	public boolean isExistingCourse(Integer courseId);
	public boolean saveCourses(Course course);
	public void updateCourse(Course course);
	public List<Course> listAllCourses();
	public void deleteCourse(Integer courseId);
	public List<Course> getCourseById(Integer courseId);
	public Integer getIdForCourse(String courseNumber);
	public List<Instructor> getInstructorsForCourse(Integer courseId);
	public List<Course> searchCourses(String courseNumber, String courseName, String areaOfInterest, String instructorName);
}
